import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Class MoveToFront.
 * @author V. Pavan Kumar
 */
public class MoveToFront {
    
    /**
     * Extended ASCII Characters Array.
     */
    private static char[] extendedASCII;    

    /**
     * Method encode.
     */
    public static void encode() {

        fillExteASCII();

        while (!BinaryStdIn.isEmpty()) {

            final char ch = BinaryStdIn.readChar();
            int j;

            for (j = 0; j < extendedASCII.length; j++) {

                if (extendedASCII[j] == ch) {
                    BinaryStdOut.write((char) j);
                    break;
                }
            }

            shiftToFront(ch, extendedASCII, j);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * Method decode.
     */
    public static void decode() {

        fillExteASCII();

        while (!BinaryStdIn.isEmpty()) {

            final char hex = BinaryStdIn.readChar();

            BinaryStdOut.write(extendedASCII[hex]);

            shiftToFront(extendedASCII[hex], extendedASCII, hex);

        }

        BinaryStdIn.close();
        ;
        BinaryStdOut.close();
    }

    /**
     * @param ch       character which is to be moved to front of the array
     * @param extASCII Array that consists 0 - 255 ASCII Characters
     * @param j        position of the character which is to be moved front
     */
    private static void shiftToFront(final char ch, final char[] extASCII, final int j) {

        for (int i = j; i > 0; i--) {
            extASCII[i] = extASCII[i - 1];
        }

        extASCII[0] = ch;
    }

    /**
     * This method is used to load ASCII Characters into the extendedASCII array.
     */
    private static void fillExteASCII() {
        
        extendedASCII = new char[256];

        for (int i = 0; i < extendedASCII.length; i++) {
            extendedASCII[i] = (char) i;
        }
    }

    /**
     * @param args Command Line Arguments
     */
    public static void main(final String[] args) {
        
        if (args[0].equals("-")) {
            MoveToFront.encode();
        } else if (args[0].equals("+")) {
            MoveToFront.decode();
        }
    }
}

            
// int j;

// for (j = 0; j < extendedASCII.length; j++) {

//     if (extendedASCII[j] == extendedASCII[hex]) {
//         break;
//     }
// }

// shiftToFront(extendedASCII[hex], extendedASCII, j);
