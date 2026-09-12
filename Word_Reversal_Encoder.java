public class Word_Reversal_Encoder {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i])
                    .reverse()
                    .toString();
            result += reversed;

            if (i < words.length - 1) {
                result += " ";
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }
}
