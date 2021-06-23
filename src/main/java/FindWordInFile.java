import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class FindWordInFile {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("WordInFile")
                .getOrCreate();

        String inputFile = "hdfs://sample.txt";
        String result = wordInFile(inputFile, spark, "world");
        System.out.println(result);
        spark.stop();
    }

    public static String wordInFile(String inputFile, SparkSession spark, String word) {
        Dataset<String> lines = spark.read().textFile(inputFile).cache();
        long linesWithWord = lines.filter((FilterFunction<String>) line -> line.contains(word)).count();
        if(linesWithWord > 0L) {
            return String.format("Keyword \"%s\" exists in given file", word);
        }
        return String.format("Keyword \"%s\" does not exist in given file", word);
    }
}
