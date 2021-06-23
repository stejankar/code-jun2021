import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReverseStringTest {
    private ReverseString reverseString;

    @BeforeAll
    public void setUp() throws Exception {
        reverseString = new ReverseString();
    }

    @AfterAll
    public void tearDown() {
        reverseString = null;
    }

    @Test
    @DisplayName("Simple character array shall be reversed")
    public void testReverse() {
        char[] inputArray = {'H','a','p','p','y'};
        char[] reverseArray = {'y','p','p','a','H'};
        try {
            reverseString.reverseString(inputArray);
            Assertions.assertArrayEquals(reverseArray,
                    inputArray,
                    "Simple character array shall be reversed");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Null array shall return same array")
    public void testReverseForNull() {
        char[] inputArray = null;
        try {
            reverseString.reverseString(inputArray);
            Assertions.assertEquals(null,
                    inputArray,
                    "Null array shall return same array");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Too large array shall throw exception")
    public void testReverseForLargeArray() {
        char[] inputArray = randomCharArray(100001);
        Exception thrown = assertThrows(
                Exception.class,
                () -> reverseString.reverseString(inputArray),
                "Expected reverseString() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("Input array too long."));
    }

    public char[] randomCharArray(int len) {
        String alphabet =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "0123456789";

        StringBuilder b = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randIdx = new Random().nextInt(alphabet.length());
            char randChar = alphabet.charAt(randIdx);
            b.append(randChar);
        }

        return b.toString().toCharArray();
    }
}
