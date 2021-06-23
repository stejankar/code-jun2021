public class ReverseString {
    public void reverseString(char[] input) throws Exception {
        if(input != null) {
            if(input.length > 100000)
                throw new Exception("Input array too long.");
            int left = 0, right = input.length - 1;
            while (left < right) {
                char tmp = input[left];
                input[left++] = input[right];
                input[right--] = tmp;
            }
        }
    }
}
