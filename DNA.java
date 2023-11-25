import java.util.ListIterator;
import java.lang.Comparable;

/**
 * A class to represent the DNA sequence.
 * 
 * @author Stephen Hogeman
 */
public class DNA extends DoubleLinkedList<DNA.Base> implements Comparable<DNA> {

    /**
     * The enum class to represent the different amino acids./n
     * Enum essentially creates new static fields of the 4 acids.
     */
    public enum Base {
        A('A'), C('C'), G('G'), T('T');

        private char base;

        private Base(char base) {
            this.base = base;
        }

        public char getLetter() {
            return base;
        }
    }

    /**
     * The constructor for the class.
     */
    public DNA() {
        super();
    }

    /**
     * Overrides the toString() method of Object.
     * Returns a String representation of the DNA sequence.
     * 
     * @return a String representation of the DNA sequence.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Base e : this) {
            s.append(e);
        }
        return s.toString();
    }

    /**
     * Given a String, returns the DNA sequence of said String.
     * If the String is empty, or an input that isn't A, G, C, or T is in the
     * String,
     * an IllegalArgumentException is thrown.
     * 
     * @return The DNA sequence of an input string
     * @throws IllegalArgumentException
     */
    public static DNA string2DNA(String s) throws IllegalArgumentException {
        DNA sequence = new DNA();
        if (s.length() == 0)
            throw new IllegalArgumentException();
        else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'A')
                    sequence.addToBack(DNA.Base.A);
                else if (s.charAt(i) == 'G')
                    sequence.addToBack(DNA.Base.G);
                else if (s.charAt(i) == 'C')
                    sequence.addToBack(DNA.Base.C);
                else if (s.charAt(i) == 'T')
                    sequence.addToBack(DNA.Base.T);
                else
                    throw new IllegalArgumentException();
            }
        }
        return sequence;
    }

    /**
     * Takes an input DNA sequence, removes numbases from the beginning of the input
     * sequence.
     * Appends new input sequence to this sequence.
     * 
     * @param dna      the input DNA sequence
     * @param numBases the amount of bases that are intially removed before
     *                 appending
     */
    public void splice(DNA dna, int numBases) {
        for (int i = 0; i < numBases; i++) {
            dna.removeFromFront();
        }
        this.append(dna);
    }

    /**
     * Returns true if the last n bases of the first input match the first n bases
     * of
     * the second input.
     * 
     * @return true if the last n bases of the first input match the first n bases
     *         of
     *         the second input.
     */
    public static boolean overlaps(DNA dna1, DNA dna2, int n) {
        DLNode nodeptr1 = dna1.getBack();
        for (int i = 1; i < n; i++) {
            nodeptr1 = nodeptr1.getPrevious();
        }
        DLNode nodeptr2 = dna2.getFront();
        for (int i = 0; i < n; i++) {
            if (!nodeptr1.getElement().equals(nodeptr2.getElement()))
                return false;
            else {
                nodeptr1.getPrevious();
                nodeptr2.getNext();
            }
        }
        return true;
    }

    /**
     * The main method of the DNA class.
     */
    public static void main(String[] args) {

        /** Instances of DNA created to store DNA. */
        DNA d1 = new DNA();
        DNA d2 = new DNA();

        if (args.length != 2)
            System.out.println("2 Strings were not input!");
        else {
            try {
                d1 = DNA.string2DNA(args[0]);
                d2 = DNA.string2DNA(args[1]);

                /** Amount that each DNA overlaps. */
                int overlap1 = 0;
                int overlap2 = 0;

                /** A counter for loops through both lists. */
                int counter = 0;
                /**
                 * Loop Goal: Determine whether the first DNA sequence overlaps more over
                 * than the second than vice versa.
                 * Loop Condition: The two strings have been made into DNA sequences, and
                 * the overlap values are 0.
                 */
                while (counter < args[0].length() && counter < args[1].length()) {
                    if (overlaps(d1, d2, counter)) {
                        overlap1 = counter;
                        counter++;
                    } else if (overlaps(d2, d1, counter)) {
                        overlap2 = counter;
                        counter++;
                    } else
                        counter++;
                }
                if (overlap1 > overlap2) {
                    d1.splice(d2, overlap1);
                    System.out.println(d1);
                } else if (overlap2 > overlap1) {
                    d2.splice(d1, overlap2);
                    System.out.println(d2);
                }
            } catch (IllegalArgumentException b) {
                System.out.println("Input included an input that is not a base!");
            }
        }
    }

    /**
     * Method compareTo() overrides the compareTo() method of the Comparable
     * interface.
     * 
     * @param dna the input dna sequence to be compared to.
     * @return 0 if the 2 sequences are equal, in which they will be alphabetized.
     * @return -1 if the input sequence is
     */
    @Override
    public int compareTo(DNA dna) {
        ListIterator<DNA.Base> it1 = this.iterator();
        ListIterator<DNA.Base> it2 = dna.iterator();

        if (it1.equals(it2))
            return 0;
        /**
         * Loop Goal: Iterator through this sequence.
         * Loop Condition: this is a DNA sequence, and the 2 sequences are not equal.
         */
        while (it1.hasNext()) {
            /**
             * Loop Goal: Iterate through the input DNA sequence.
             * Loop Pre-Condition: input is a DNA sequence, and the 2 sequences compared
             * are not equals.
             */
            while (it2.hasNext()) {
                if (it1.next().getLetter() < it2.next().getLetter())
                    return -1;
                else if (it1.next().getLetter() > it2.next().getLetter())
                    return 1;
                else {
                    it1.next();
                    it2.next();
                }
            }
            return 1;
        }
        return -1;
    }
}