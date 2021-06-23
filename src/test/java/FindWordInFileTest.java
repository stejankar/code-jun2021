import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindWordInFileTest {
    private static FindWordInFile findWordInFile;
    private static SparkSession spark;

    @BeforeAll
    public static void setUp() {
        spark = SparkSession
                .builder()
                .appName("JavaWordCount")
                .master("local[1]")
                .getOrCreate();
        findWordInFile = new FindWordInFile();
    }

    @AfterAll
    public static void tearDown() {
        spark.stop();
        spark = null;
    }

    @Test
    @DisplayName("If word exist in file then message shown accordingly")
    public void testWordExistsInFile() {
        String inputFile = "src/test/resources/sample.txt";
        assertEquals("Keyword \"world\" exists in given file", FindWordInFile.wordInFile(inputFile, spark, "world"));
    }

    @Test
    @DisplayName("If word does not exist in file then message shown accordingly")
    public void testWordDoesNotExistsInFile() {
        String inputFile = "src/test/resources/sample.txt";
        assertEquals("Keyword \"abcd\" does not exist in given file", FindWordInFile.wordInFile(inputFile, spark, "abcd"));
    }
}
