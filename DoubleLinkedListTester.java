import org.junit.*;
import static org.junit.Assert.*;
import java.util.ListIterator;

/**
 * A tester for the DoubleLinkedList Class
 * 
 * @author Stephen Hogeman
 */
public class DoubleLinkedListTester<T> extends DoubleLinkedList<T> {

    /**
     * Method tests the equals() method of the DoubleLinkedList class
     */
    @Test
    public void testEquals() {

        // Create the first list that has elements in the order of 3,2,1.
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
        list1.addToFront(1);
        list1.addToFront(2);
        list1.addToFront(3);

        // Create the second list that has elements in the order 3,2,2.
        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
        list2.addToFront(2);
        list2.addToFront(2);
        list2.addToFront(3);

        // Create a third list that has the elements in the order 3,3,1.
        DoubleLinkedList<Integer> list3 = new DoubleLinkedList<Integer>();
        list3.addToFront(1);
        list3.addToFront(3);
        list3.addToFront(3);

        // Create a fourth list that has the elements in the order 4,2,1.
        DoubleLinkedList<Integer> list4 = new DoubleLinkedList<Integer>();
        list4.addToFront(1);
        list4.addToFront(2);
        list4.addToFront(4);

        // Create a fifth list that is empty
        DoubleLinkedList<Integer> list5 = new DoubleLinkedList<Integer>();

        // Create a list that is very similar to list 1 but has 1 element more.
        DoubleLinkedList<Integer> list6 = new DoubleLinkedList<Integer>();
        list6.addToFront(1);
        list6.addToFront(2);
        list6.addToFront(3);
        list6.addToBack(4);

        // Create a seventh list that has many more elements than list 1.
        DoubleLinkedList<Integer> list7 = new DoubleLinkedList<Integer>();
        list7.addToFront(1);
        list7.addToFront(2);
        list7.addToFront(3);
        list7.addToBack(1);
        list7.addToBack(2);
        list7.addToBack(3);

        // Create an eigth list that is equal to list 1
        DoubleLinkedList<Integer> list8 = new DoubleLinkedList<Integer>();
        list8.addToFront(1);
        list8.addToFront(2);
        list8.addToFront(3);

        // The JUnit testers for all of the test cases
        assertFalse("Testing if the last element is not equals.",
                list1.equals(list2)); // Test 1
        assertFalse("Testing if the 2nd element is not equals.",
                list1.equals(list3)); // Test 2
        assertFalse("Testing if the first element is not equals.",
                list1.equals(list4)); // Test 3
        assertFalse("Testing if an empty list is compared.",
                list1.equals(list5)); // Test 4
        assertFalse("Testing if a list that is one longer is not equals.",
                list1.equals(list6)); // Test 5
        assertFalse("Testing if a list that is many elements longer is not equals.",
                list1.equals(list7)); // Test 6
        assertTrue("Testing 2 equals lists.",
                list1.equals(list8)); // Test 7
    }

    /**
     * Method tests the append() method of the DoubleLinkedList class
     */
    @Test
    public void testAppend() {

        // Create the first list that is empty
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
        list1.addToFront(23);

        // Create the second list that is empty
        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();

        // Create the third list that has 1 element
        DoubleLinkedList<Integer> list3 = new DoubleLinkedList<Integer>();
        list3.addToFront(1);

        // Create the fourth list that has 3 elements
        DoubleLinkedList<Integer> list4 = new DoubleLinkedList<Integer>();
        list4.addToFront(1);
        list4.addToFront(2);

        // Create the fifth list that has 5 elements
        DoubleLinkedList<Integer> list5 = new DoubleLinkedList<Integer>();
        list5.addToFront(1);
        list5.addToFront(2);
        list5.addToFront(3);
        list5.addToBack(1);
        list5.addToBack(2);

        // List2 appends list1 (Testing a list appending an empty list).
        list1.append(list2);
        assertNull("Tests the next element of list 1 (null).",
                list1.getFront().getNext()); // Test 1

        // List1 appends list3 (Testing a list of 1 appending an empty list).
        list3.append(list1);
        assertEquals("Tests the first element of list3.", 1.0,
                list3.getFront().getElement(), 0.1); // Test 2
        assertEquals("Tests whether the next element in the list is 23.", 23.0,
                list3.getFront().getNext().getElement(), 0.1); // Test 3

        // List3 appends list4 (Testing a list of 2 appending a list of 1).
        list4.append(list3);
        assertEquals("Tests the 1st element of list4.", 2.0,
                list4.getFront().getElement(), 0.1); // Test 4
        assertEquals("Tests the 2nd element of the previous list.", 1.0,
                list4.getFront().getNext().getElement(), 0.1); // Test 5
        assertEquals("Tests the 3rd element of the previous list.", 1.0,
                list4.getFront().getNext().getNext().getElement(), 0.1); // Test 6
        assertNull("Tests whether the next element in the list is null.",
                list4.getFront().getNext().getNext().getNext().getNext()); // Test 7

        // List4 appends list5 (Testing a list of 5 appending a list of 3).
        list5.append(list4);
        assertEquals("Tests the 1st element of list5.", 3.0,
                list5.getFront().getElement(), 0.1); // Test 8
        assertEquals("Tests the 3rd element of list5.", 1.0,
                list5.getFront().getNext().getNext().getElement(), 0.1); // Test 9
        assertEquals("Tests the final element of list5.", 23.0,
                list5.getBack().getElement(), 0.1); // Test 10
        assertNull("Tests whether the next element in the list is null.",
                list5.getBack().getNext()); // Test 11
    }

    /**
     * Tests the linked list iterator.
     */
    @Test
    public void testListIterator() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        for (int i = 5; i > 0; i--)
            list.addToFront(i);

        /* checking that we get out what we put it */
        int i = 1;
        for (Integer x : list)
            assertEquals("Testing value returned by iterator", new Integer(i++), x);
        // Test 1

        if (i != 6)
            fail("The iterator did not run through all the elements of the list");
    }

    /**
     * Method tests the add() method of the DoubleLinkedList class.
     * Method add() overrides the add() method of the ListIterator interface.
     * add() inserts a specified element into a list
     */
    @Test
    public void testAdd() {

        // Create a list that has 0 elements
        DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
        ListIterator<String> it1 = list1.iterator();
        it1.add("No");
        it1.add("Yes");

        // Create a list that has 1 element
        DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
        list2.addToFront("Hello");
        ListIterator<String> it2 = list2.iterator();
        it2.add("Yo");

        // Create a list that has 3 elements
        DoubleLinkedList<String> list3 = new DoubleLinkedList<String>();
        list3.addToFront("Howdy");
        list3.addToFront("Hi");
        list3.addToFront("Bye");
        ListIterator<String> it3 = list3.iterator();
        it3.add("Yeah");
        it3.add("Yup");
        it3.add("Cane");

        assertEquals("Testing an empty iterator adding two values. Front",
                "No", list1.getFront().getElement()); // Test 1
        assertEquals("Testing an empty iteartor adding two values. Back",
                "Yes", list1.getBack().getElement()); // Test 2
        assertEquals("Testing an iterator adding 1 element. Original Element.",
                "Hello", list2.getFront().getElement()); // Test 3
        assertEquals("Testing an iterator adding 1 element. Added Element.",
                "Yo", list2.getFront().getNext().getElement()); // Test 4
        assertEquals("Testing an iterator adding 3 elements. First added element.",
                "Yeah", list3.getBack().getPrevious().getPrevious().getElement()); // Test 5
        assertEquals("Testing an iterator adding 3 elements. Last added element.",
                "Cane", list3.getBack().getElement()); // Test 6
    }

 /**
 * Method tests the hasNext() method of the DoubleLinkedList class.
 * Method hasNext() overrides the method hasNext() of the ListIterator interface.
 */
 @Test
 public void testHasNext() {
 
 //Create a list that has no elements.
 DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
 ListIterator<String> it1 = list1.iterator();
 
 //Create a list that has 1 element.
 DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
 list2.addToFront("Yup");
 ListIterator<String> it2 = list2.iterator();
 
 //Create a list with 2 elements.
 DoubleLinkedList<String> list3 = new DoubleLinkedList<String>();
 list3.addToFront("Z");
 list3.addToFront("Y");
 ListIterator<String> it3 = list3.iterator();
 
 //Both of these tests return false, as neither iterator has another element 
after the first one.
 assertFalse("Testing an empty iterator.", 
it1.hasNext()); //Test 1
 assertTrue("Testing an iterator with 1 element.", 
it2.hasNext()); //Test 2
 
 //Testing an iterator with 2 values. This will test 2 times.
 assertTrue("Testing an iterator with 2 elements. First", 
it3.hasNext()); //Test 3
 it3.next();
 assertTrue("Testing an iterator with 2 elements. Second", 
it3.hasNext()); //Test 4
 it3.next();
 assertFalse("Testing an iterator with 2 elements. Outside.", 
it3.hasNext()); //Test 5
 }

 /**
 * Method tests the hasPrevious() method of the DoubleLinkedList class.
 * Method hasPrevious() overrides the method hasPrevious() of the ListIterator 
interface.
 */
 @Test
 public void testHasPrevious() {
 
 //Create a list that has no elements.
 DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
 ListIterator<String> it1 = list1.iterator();
 
 //Create a list that has 1 element.
 DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
 list2.addToFront("Yowdle");
 ListIterator<String> it2 = list2.iterator();
 
 //Create a list with 2 elements.
 DoubleLinkedList<String> list3 = new DoubleLinkedList<String>();
 list3.addToFront("Zebra");
 list3.addToFront("Yellow");
 ListIterator<String> it3 = list3.iterator();
 it3.next();
 
 //Both of these tests return false, as neither iterator has another element 
after the first one.
 assertFalse("Testing an empty iterator.", 
it1.hasPrevious()); //Test 1
 assertTrue("Testing an iterator with 1 element.", 
it2.hasPrevious()); //Test 2
 
 //Testing an iterator with 2 values. This will test 2 times.
 assertTrue("Testing an iterator with 2 elements. Last", 
it3.hasPrevious()); //Test 3
 it3.previous();
 assertTrue("Testing an iterator with 2 elements. First", 
it3.hasPrevious()); //Test 4
 it3.previous();
 assertFalse("Testing an iterator with 2 elements. Outside.", 
it3.hasPrevious()); //Test 5
 
 }

    /**
     * Method tests the next() method of the DoubleLinkedList class.
     * Method next() overrides the method next() of the ListIterator interface.
     */
    @Test
    public void testNext() {

        // Create a list that has no elements.
        DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
        ListIterator<String> it1 = list1.iterator();

        // Create a list that has 1 element.
        DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
        list2.addToFront("Yup");
        ListIterator<String> it2 = list2.iterator();

        // Create a list with 2 elements.
        DoubleLinkedList<String> list3 = new DoubleLinkedList<String>();
        list3.addToFront("Z");
        list3.addToFront("Y");
        ListIterator<String> it3 = list3.iterator();

        // Testing an iterator that doesn't have a next element.
        assertNull("Testing an iterator that is empty.",
                it1.next()); // Test 1

        // Testing an iterator that has 1 element
        assertEquals("Testing the only element of an iterator.",
                "Yup", it2.next()); // Test 2
        assertNull("Testing that it has iterated.",
                it2.next()); // Test 3

        // Testing an iterator with 2 elements.
        assertEquals("Testing an iterator with 2 elements, first element to second.",
                "Y", it3.next()); // Test 4
        assertEquals("Testing an iterator with 2 elements, second element to end.",
                "Z", it3.next()); // Test 5
        assertNull("Testing an iterator with 2 elements, end of iterator.",
                it3.next()); // Test 6
    }

    /**
     * Method tests the previous() method of the DoubleLinkedList class.
     * Method previous() overrides the method previous() of the ListIterator
     * interface.
     */
    @Test
    public void testPrevious() {

        // Create a list that has no elements.
        DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
        ListIterator<String> it1 = list1.iterator();

        // Create a list that has 1 element.
        DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
        list2.addToFront("Yowdle");
        ListIterator<String> it2 = list2.iterator();

        // Create a list with 2 elements.
        DoubleLinkedList<String> list3 = new DoubleLinkedList<String>();
        list3.addToFront("Zebra");
        list3.addToFront("Yellow");
        ListIterator<String> it3 = list3.iterator();
        it3.next();

        // Testing an iterator that doesn't have a previous element.
        assertNull("Testing an iterator that is empty.",
                it1.previous()); // Test 1

        // Testing an iterator that has 1 element
        assertEquals("Testing the only element of an iterator.",
                "Yowdle", it2.previous()); // Test 2
        assertNull("Testing that it has iterated.",
                it2.previous()); // Test 3

        // Testing an iterator with 2 elements.
        assertEquals("Testing an iterator with 2 elements, second element to first.",
                "Zebra", it3.previous()); // Test 4
        assertEquals("Testing an iterator with 2 elements, first element to front.",
                "Yellow", it3.previous()); // Test 5
    }

    /**
     * Method tests the set() method of the DoubleLinkedList class.
     * Method set() overrides the method set() of the ListIterator interface.
     */
    @Test
    public void testSet() {

        // Create an iterator that has 1 element that can be set.
        DoubleLinkedList<String> list1 = new DoubleLinkedList<String>();
        list1.addToFront("Saturday");
        ListIterator<String> it1 = list1.iterator();

        // Create an iterator that has multiple elements that can be set.
        DoubleLinkedList<String> list2 = new DoubleLinkedList<String>();
        list2.addToFront("Mumbo!");
        list2.addToFront("Means");
        list2.addToFront("M");
        ListIterator<String> it2 = list2.iterator();

        // Testing an iterator with 1 element to be set.
        assertEquals("Testing the list before the element is set.", "Saturday",
                list1.getFront().getElement()); // Test 1
        it1.set("Wednesday");
        assertEquals("Testing the list after the element is set.", "Wednesday",
                list1.getFront().getElement()); // Test 2

        // Testing an iterator with multiple elements.
        assertEquals("Testing the list before the element is set.", "M",
                list2.getFront().getElement()); // Test 3
        assertEquals("Testing the list before the element is set.", "Means",
                list2.getFront().getNext().getElement()); // Test 4
        assertEquals("Testing the list before the element is set.", "Mumbo!",
                list2.getFront().getNext().getNext().getElement()); // Test 5
        it2.set("W");
        it2.next();
        it2.set("Also Means");
        it2.next();
        it2.set("Wumbo!");
        assertEquals("Testing if the first element was set.", "W",
                list2.getFront().getElement()); // Test 6
        assertEquals("Testing if the second element was set.", "Also Means",
                list2.getFront().getNext().getElement()); // Test 7
        assertEquals("Testing if the last element was set.", "Wumbo!",
                list2.getFront().getNext().getNext().getElement()); // Test 8
    }

    /**
     * Tests the remove() method of the DoubleLinkedList class, which throws an
     * exception.
     */
    @Test
    public void testRemove() {
        try {
            DNA dna = new DNA();
            ListIterator<DNA.Base> it = dna.iterator();
            it.remove();
        } catch (UnsupportedOperationException e) { // Test 1
        }
    }

    /**
     * Tests the previousIndex() method of the DoubleLinkedList class, which throws
     * an exception.
     */
    @Test
    public void testPreviousIndex() {
        try {
            DNA dna = new DNA();
            ListIterator<DNA.Base> it = dna.iterator();
            it.previousIndex();
        } catch (UnsupportedOperationException e) { // Test 1
        }
    }

    /**
     * Tests the nextIndex() method of the DoubleLinkedList class, which throws an
     * exception.
     */
    @Test
    public void testNextIndex() {
        try {
            DNA dna = new DNA();
            ListIterator<DNA.Base> it = dna.iterator();
            it.nextIndex();
        } catch (UnsupportedOperationException e) { // Test 1
        }
    }
}