import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindWordInFileTest {
    private static JavaSparkContext sparkCtx;
    private static FindWordInFile findWordInFile;

    @BeforeAll
    public static void setUp() {
        SparkConf conf = new SparkConf();
        conf.setMaster("local[2]");
        conf.setAppName("junit");
        sparkCtx = new JavaSparkContext(conf);
        findWordInFile = new FindWordInFile();
    }

    @AfterAll
    public static void tearDown() {
        sparkCtx = null;
    }

    @Test
    @DisplayName("If word exist in file then message shown accordingly")
    public void testWordExistsInFile() {
        String inputFile = "src/test/resources/sample.txt";
        assertEquals("Keyword \"world\" exists in given file", FindWordInFile.wordInFile(inputFile, sparkCtx, "world"));
    }

    @Test
    @DisplayName("If word does not exist in file then message shown accordingly")
    public void testWordDoesNotExistsInFile() {
        String inputFile = "src/test/resources/sample.txt";
        assertEquals("Keyword \"abcd\" does not exist in given file", FindWordInFile.wordInFile(inputFile, sparkCtx, "abcd"));
    }
}
