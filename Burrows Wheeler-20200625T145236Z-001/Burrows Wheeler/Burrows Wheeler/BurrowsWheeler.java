import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Class BurrowsWheeler.
 * @author V. Pavan Kumar
 */
public class BurrowsWheeler {

    /**
     * Method transform.
     */
    public static void transform() {

        final String str = BinaryStdIn.readString();

        final CircularSuffixArray csaObj = new CircularSuffixArray(str);

        for (int idx = 0; idx < str.length(); idx++) {

            if (csaObj.index(idx) == 0) {
                BinaryStdOut.write(idx);
                break;
            }
        }

        for (int j = 0; j < str.length(); j++) {

            if (csaObj.index(j) == 0) {
                BinaryStdOut.write(str.charAt(str.length() - 1));
            } else {
                BinaryStdOut.write(str.charAt(csaObj.index(j) - 1));
            }
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * Method inverseTransform.
     */
    public static void inverseTransform() {

        int first = BinaryStdIn.readInt();

        final char[] t = BinaryStdIn.readString().toCharArray();
        final char[] t1 = t.clone();

        sort(t1, 1);

        final ArrayList<Integer> nextListObj = new ArrayList<>(t.length);

        final HashMap<Character, ArrayList<Integer>> hashMapObj = new HashMap<>(t.length);

        for (int i = 0; i < t.length; i++) {

            if (hashMapObj.get(t[i]) == null) {
                hashMapObj.put(t[i], new ArrayList<>());
            } 

            hashMapObj.get(t[i]).add(i);
        }

        for (int i = 0; i < t.length; i++) {
            nextListObj.add(hashMapObj.get(t1[i]).get(0));
            hashMapObj.get(t1[i]).remove(0);
        }

        for (int i = 0; i < nextListObj.size(); i++) {
            BinaryStdOut.write(t1[first]);
            first = nextListObj.get(first);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * @param t character array 
     * @param w width of the char
     */
    private static void sort(final char[] t, final int w) {

        final int n = t.length;
        final int R = 256;
        final char[] aux = new char[n];

        for (int d = w - 1; d >= 0; d--) {

            final int[] count = new int[R + 1];

            for (int i = 0; i < n; i++) {
                count[t[i] + 1]++;
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < n; i++) {
                aux[count[t[i]]++] = t[i];
            }

            for (int i = 0; i < n; i++) {
                t[i] = aux[i];
            }
        }
    }

    /**
     * @param args Command Line Arguments
     */
    public static void main(final String[] args) {
        
        if (args[0].equals("-")) {
            BurrowsWheeler.transform();
        } else if (args[0].equals("+")) {
            BurrowsWheeler.inverseTransform();
        }
    }
}




// https://coursera.cs.princeton.edu/algs4/assignments/burrows/specification.php