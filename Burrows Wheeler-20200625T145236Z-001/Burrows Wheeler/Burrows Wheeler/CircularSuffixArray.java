import java.lang.IllegalArgumentException;
import java.util.Arrays;

/**
 * Class CircularSuffixArray.
 * @author V. Pavan Kumar
 */
public class CircularSuffixArray {
    
    /**
     * Integer Array indexArr that consists the position of sorted
     * suffix in original suffixes.
     */
    private final int[] indexArr;

    /**
     * Class Suffix.
     */
    private static class Suffix implements Comparable<Suffix> {

        /**
         * String text.
         */
        private final String text;

        /**
         * Integer index.
         */
        private final int index;

        /**
         * Argumented Constructor for initializing instance variables.
         */
        private Suffix(final String text, final int index) {

            this.text = text;
            this.index = index;
        }

        /**
         * @return length of the text
         */
        private int length() {
            return text.length();
        }

        /**
         * @param i pos at which character is required
         * @return returns the character present in text at the given position
         */
        private char charAt(final int i) {
            return text.charAt(i);
        }

        /**
         * @param that another suffix
         * @return integer based on difference between character present at this text at position i
         * and that text at position i
         */
        public int compareTo(final Suffix that) {

            if (this == that) {
                return 0;
            }

            final int n = Math.min(this.length(), that.length());

            for (int i = 0; i < n; i++) {

                if (this.charAt(i) < that.charAt(i)) {
                    return -1;
                }

                if (this.charAt(i) > that.charAt(i)) {
                    return +1;
                }
            }

            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index);
        }
    }

    /**
     * Argumented Constructor for initializing instance variables.
     */
    public CircularSuffixArray(final String text) {

        if (text == null) {
            throw new IllegalArgumentException();
        }

        final int length = text.length();

        final Suffix[] suffixes = new Suffix[length];

        for (int index = 0; index < length; index++) {
            suffixes[index] = new Suffix(text.substring(index) + text.substring(0, index), index);
        }

        // sort(suffixes, length); 
        Arrays.sort(suffixes);

        indexArr = new int[length];

        for (int j = 0; j < length; j++) {
            indexArr[j] = suffixes[j].index;
        }
    }

    /**
     * @return text length
     */
    public int length() {
        return indexArr.length;
    }

    /**
     * @param i index position
     * @return i position in original suffixes
     */
    public int index(final int i) {

        if (i < 0 || i >= indexArr.length) {
            throw new IllegalArgumentException();
        }
        
        return indexArr[i];
    }

    /**
     * @param suffixes suffix array
     * @param w width of the text
     */
    private static void sort(final Suffix[] suffixes, final int w) {

        final int n = suffixes.length;
        final int R = 256;
        final Suffix[] aux = new Suffix[n];

        for (int d = w - 1; d >= 0; d--) {

            final int[] count = new int[R + 1];

            for (int i = 0; i < n; i++) {
                count[suffixes[i].text.charAt(d) + 1]++;
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < n; i++) {
                aux[count[suffixes[i].text.charAt(d)]++] = suffixes[i];
            }

            for (int i = 0; i < n; i++) {
                suffixes[i] = aux[i];
            }
        }
    }

    /**
     * @param args Command Line Arguments
     */
    public static void main(final String[] args) {
        // final CircularSuffixArray csaObj = new CircularSuffixArray("ABRACADABRA!");
        // System.out.println(csaObj.index(11));
        // System.out.println(csaObj.length());
    }
}