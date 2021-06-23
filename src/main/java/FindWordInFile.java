import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FindWordInFile {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("Read Multiple Text Files to RDD")
                .set("spark.executor.memory","2g");
        // start a spark context
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        String inputFile = "hdfs://sample.txt";
        String result = wordInFile(inputFile, sc, "world");
        System.out.println(result);
        sc.stop();
    }

    public static String wordInFile(String inputFile, JavaSparkContext sc, String word) {
        JavaRDD<String> lines = sc.textFile(inputFile, 1);
        boolean wordExists = false;
        for(String line:lines.collect()){
            if(line.contains(word)) {
                return String.format("Keyword \"%s\" exists in given file", word);
            }
        }
        return String.format("Keyword \"%s\" does not exist in given file", word);
    }
}
