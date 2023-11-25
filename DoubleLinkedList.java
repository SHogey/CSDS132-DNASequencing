import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ListIterator;

/**
 * A doubly linked linked list.
 * 
 * @author Stephen Hogeman
 */
public class DoubleLinkedList<T> implements Iterable<T> {

    /** a reference to the first node of the double linked list */
    private DLNode<T> front;

    /** a reference to the last node of a double linked list */
    private DLNode<T> back;

    /** Create an empty double linked list. */
    public DoubleLinkedList() {
        front = back = null;
    }

    /**
     * Returns true if the list is empty.
     * 
     * @return true if the list has no nodes
     */
    public boolean isEmpty() {
        return (getFront() == null);
    }

    /**
     * Returns the reference to the first node of the linked list.
     * 
     * @return the first node of the linked list
     */
    protected DLNode<T> getFront() {
        return front;
    }

    /**
     * Sets the first node of the linked list.
     * 
     * @param node the node that will be the head of the linked list.
     */
    protected void setFront(DLNode<T> node) {
        front = node;
    }

    /**
     * Returns the reference to the last node of the linked list.
     * 
     * @return the last node of the linked list
     */
    protected DLNode<T> getBack() {
        return back;
    }

    /**
     * Sets the last node of the linked list.
     * 
     * @param node the node that will be the last node of the linked list
     */
    protected void setBack(DLNode<T> node) {
        back = node;
    }

    /**
     * Add an element to the head of the linked list.
     * 
     * @param element the element to add to the front of the linked list
     */
    public void addToFront(T element) {
        DLNode<T> insert = new DLNode<T>(element, null, getFront());
        if (isEmpty())
            setBack(insert);
        setFront(insert);
    }

    /**
     * Add an element to the tail of the linked list.
     * 
     * @param element the element to add to the tail of the linked list
     */
    public void addToBack(T element) {
        DLNode<T> insert = new DLNode<T>(element, getBack(), null);
        if (isEmpty())
            setFront(insert);
        setBack(insert);
    }

    /**
     * Remove and return the element at the front of the linked list.
     * 
     * @return the element that was at the front of the linked list
     * @throws NoSuchElementException if attempting to remove from an empty list
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        T removed = getFront().getElement();
        setFront(getFront().getNext());
        if (getFront() == null)
            setBack(null);
        return removed;
    }

    /**
     * Remove and return the element at the back of the linked list.
     * 
     * @return the element that was at the back of the linked list
     * @throws NoSuchElementException if attempting to remove from an empty list
     */
    public T removeFromBack() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        T removed = getBack().getElement();
        setBack(getBack().getPrevious());
        if (getBack() == null)
            setFront(null);
        return removed;
    }

    /**
     * Returns an interator for the linked list that starts at the head of the list
     * and runs to the tail.
     * 
     * @return the iterator that runs through the list from the head to the tail
     */
    @Override
    public ListIterator<T> iterator() {
        return new ListIterator<T>() {

            /** A nodeptr for the anonymous class in iterator(). */
            private DLNode<T> nodeptr = getFront();

            /**
             * Overrides the add() method of the ListIterator<T> interface.
             * 
             * @param element the input element to be added to the iterator.
             */
            @Override
            public void add(T element) {
                if (isEmpty())
                    setFront(new DLNode<T>(element, null, null));
                else
                    setBack(new DLNode<T>(element, getBack(), null));
            }

            /**
             * Overrides the hasNext() method of the ListIterator<T> interface.
             */
            @Override
            public boolean hasNext() {
                if (isEmpty())
                    return false;
                else
                    return nodeptr != null;
            }

            /**
             * Overrides the hasPrevious() method of the ListIterator<T> interface.
             */
            @Override
            public boolean hasPrevious() {
                return nodeptr != null;
            }

            /**
             * Overrides the next() method of the ListIterator<T> interface.
             */
            @Override
            public T next() {
                if (this.hasNext()) {
                    T save = nodeptr.getElement();
                    nodeptr = nodeptr.getNext();
                    return save;
                } else
                    return null;
            }

            /**
             * Overrides the hasNext() method of the ListIterator<T> interface.
             * Returns the value of the previous node, and moves the nodeptr backwards.
             * 
             * @return the value of the previous node
             */
            @Override
            public T previous() {
                if (this.hasPrevious()) {
                    T save = nodeptr.getElement();
                    nodeptr = nodeptr.getPrevious();
                    return save;
                } else
                    return null;
            }

            /**
             * Overrides the set() method of the ListIterator<T> interface.
             * Sets the last element retrieved with next() or previous() to the element.
             * 
             * @param element the element that is to be set in a node
             */
            @Override
            public void set(T element) {
                nodeptr.setElement(element);
            }

            /**
             * Overrides the remove() method of the ListIterator<T> interface.
             * 
             * @throws UnsupportedOperationException
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            /**
             * Overrides the previousIndex() method of the ListIterator<T> interface.
             */
            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            /**
             * Overrides the nextIndex() method of the ListIterator<T> interface.
             */
            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Method overrides the equals() method of object. 2 linked lists are equals if
     * they contain the same
     * elements in the same order.
     * 
     * @param obj the list to be compared to
     * @return true if the lists are equal, and false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleLinkedList<?>) {
            DoubleLinkedList<?> list = (DoubleLinkedList<?>) obj;
            Iterator<T> it1 = this.iterator();
            Iterator<?> it2 = list.iterator();
            while (it1.hasNext()) {
                if (!it1.next().equals(it2.next()))
                    return false;
            }
            if (!it1.hasNext() && !it2.hasNext())
                return true;
            else if (list.isEmpty() && this.isEmpty())
                return true;
        }
        return false;
    }

    /**
     * Method appends a DoubleLinkedList to another DoubleLinkedList, upon this
     * appending, the original
     * DoubleLinkedList is destroyed.
     * 
     * @param list the DoubleLinkedList that is being appended
     */
    public void append(DoubleLinkedList<T> list) {
        Iterator<T> it = list.iterator();
        if (!this.isEmpty() && !list.isEmpty()) {
            while (it.hasNext()) {
                this.setBack(new DLNode<T>(it.next(), this.getBack(), null));
            }
        } else if (!list.isEmpty()) {
            while (it.hasNext()) {
                this.setBack(new DLNode<T>(it.next(), back, null));
            }
        } else
            ;
    }
}