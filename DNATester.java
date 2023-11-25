import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

/**
 * A JUnit tester that tests the DNA class.
 * 
 * @author Stephen Hogeman
 */
public class DNATester {

    /**
     * Method tests the toString() method of the DNA class.
     */
    @Test
    public void testToString() {

        // Test an empty segment of DNA.
        DNA dna1 = new DNA();

        // Test a segment of DNA with multiple bases.
        DNA dna2 = new DNA();
        dna2.addToFront(DNA.Base.C);
        dna2.addToFront(DNA.Base.G);
        dna2.addToFront(DNA.Base.T);
        dna2.addToFront(DNA.Base.A);

        assertEquals("Testing a list that has no bases.", "",
                dna1.toString()); // Test 1
        assertEquals("Testing a list that has multiple bases.", "ATGC",
                dna2.toString()); // Test 2
    }

    /**
     * Method tests the string2DNA() method of the DNA class.
     */
    @Test
    public void testString2DNA() {

        // Testing one base from string to DNA.
        DNA d1 = new DNA();
        DNA d2 = new DNA();
        d2.addToFront(DNA.Base.G);

        DNA dna1 = new DNA();
        String test1 = "ATC";
        DNA dna2 = new DNA();
        dna2.addToFront(DNA.Base.C);
        dna2.addToFront(DNA.Base.T);
        dna2.addToFront(DNA.Base.A);

        assertEquals("Testing one element of a String to DNA.", d2,
                d1.string2DNA("G")); // Test 1
        assertEquals("Testing multiple elements of String to DNA.", dna2,
                dna1.string2DNA("ATC")); // Test 2
        try {
            assertEquals("Testing an illegal argument input.", dna2,
                    dna1.string2DNA("atc"));
            fail("Illegal Argument!");
        } catch (IllegalArgumentException e) { // Test 3
        }
    }

    /**
     * Method tests the splice() method of the DNA class.
     */
    @Test
    public void testSplice() {

        // Creating a strand with 1 base.
        DNA dna1 = new DNA();
        dna1.addToFront(DNA.Base.A);

        // Creating a strand with 2 bases.
        DNA dna2 = new DNA();
        dna2.addToFront(DNA.Base.G);
        dna2.addToFront(DNA.Base.C);

        // Creating a strand with multiple bases.
        DNA dna3 = new DNA();
        dna3.addToFront(DNA.Base.A);
        dna3.addToFront(DNA.Base.G);
        dna3.addToFront(DNA.Base.C);
        dna3.addToFront(DNA.Base.T);
        dna3.addToFront(DNA.Base.A);

        // Strand of 1 splices a strand of 2, 0 bases removed.
        dna1.splice(dna2, 0);
        assertEquals("Testing a strand splicing a strand of 2, front.",
                DNA.Base.A, dna1.getFront().getElement()); // Test 1
        assertEquals("Testing a strand splicing a strand of 2, middle.",
                DNA.Base.C, dna1.getFront().getNext().getElement()); // Test 2
        assertEquals("Testing a strand splicing a strand of 2, end.",
                DNA.Base.G, dna1.getBack().getElement()); // Test 3

        // Strand of 2 splices a strand of 5, 3 bases removed.
        dna2.splice(dna3, 3);
        assertEquals("Tests a strand splicing a full strand 3 bases, end.",
                DNA.Base.A, dna2.getBack().getElement()); // Test 4
        assertEquals("Tests a strand splicing a full strand 3 bases, front.",
                DNA.Base.C, dna2.getFront().getElement()); // Test 5
        assertEquals("Tests a strand splicing a full strand 3 bases, middle.",
                DNA.Base.G, dna2.getFront().getNext().getNext().getElement()); // Test 6
    }

    /**
     * Method tests the overlaps() method of the DNA class.
     */
    @Test
    public void testOverlaps() {

        // Sequence of 2 created, overlaps fully.
        DNA dna1 = new DNA();
        dna1.addToFront(DNA.Base.T);
        dna1.addToFront(DNA.Base.A);

        // Sequence of 2 created, overlaps fully.
        DNA dna2 = new DNA();
        dna2.addToFront(DNA.Base.T);
        dna2.addToFront(DNA.Base.A);

        // Sequence of many created, beginning overlaps with end of dna4.
        DNA dna3 = new DNA();
        dna3.addToFront(DNA.Base.T);
        dna3.addToFront(DNA.Base.G);
        dna3.addToFront(DNA.Base.C);
        dna3.addToFront(DNA.Base.A);
        dna3.addToFront(DNA.Base.T);

        // Sequence of many created, end overlaps with beginning of dna3.
        DNA dna4 = new DNA();
        dna4.addToFront(DNA.Base.A);
        dna4.addToFront(DNA.Base.T);
        dna4.addToFront(DNA.Base.C);
        dna4.addToFront(DNA.Base.A);
        dna4.addToFront(DNA.Base.T);
        dna4.addToFront(DNA.Base.G);
        dna4.addToFront(DNA.Base.A);
        dna4.addToFront(DNA.Base.T);

        assertTrue("Testing overlaps on sequences of 2.", dna1.overlaps(dna1, dna2,
                2)); // Test 1
        assertTrue("Testing overlaps on sequences of many.", dna3.overlaps(dna4, dna3,
                2)); // Test 2
        assertFalse("Testing overlaps on sequences of many.", dna3.overlaps(dna4, dna3,
                4)); // Test 3
        assertFalse("Testing overlaps on sequences of 2.", dna1.overlaps(dna1, dna2,
                1)); // Test 4
    }

 /**
 * Tests the compareTo() method of the DNA class.
 */
 @Test
 public void testCompareTo() {
 
 //Create an instance of a DNA sequence with 1 base.
 DNA dna1 = new DNA();
 dna1.addToFront(DNA.Base.C);
 
 //Creates an instance of a DNA sequence with no bases.
 DNA dna2 = new DNA();
 
 //Creates an instance of a DNA sequence with many bases.
 DNA dna3 = new DNA();
 dna3.addToFront(DNA.Base.G);
 dna3.addToFront(DNA.Base.A);
 dna3.addToFront(DNA.Base.C);
 
 //Creates an instance of a DNA sequence with many bases (same as dna3).
 DNA dna4 = new DNA();
 dna4.addToFront(DNA.Base.G);
 dna4.addToFront(DNA.Base.T);
 dna4.addToFront(DNA.Base.A);
 
 //Creates an instance of a DNA sequence with 1 base.
 DNA dna5 = new DNA();
 dna5.addToFront(DNA.Base.C);
 
 assertEquals("Tests a sequence of 1 compared to a sequence of 0.", 1, 
dna1.compareTo(dna2), 0.1); //Test 1
 assertEquals("Tests a sequence of 0 compared to a sequence of 1.", -1, 
dna2.compareTo(dna1), 0.1); //Test 2
 assertEquals("Tests a sequence of many compared to a sequence with the same 
amount of bases.", 1, dna3.compareTo(dna4), 0.1); //Test 3
 }
}