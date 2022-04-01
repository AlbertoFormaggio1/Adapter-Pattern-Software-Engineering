package myAdapter;

import java.util.NoSuchElementException;

/**
 * <P>
 * An iterator for lists that allows the programmer
 *  to traverse the list in either direction, modify
 *  the list during iteration, and obtain the iterator's
 *  current position in the list. A <code>HListIterator</code>
 *  has no current element; its <I>cursor position</I> always
 *  lies between the element that would be returned by a call
 *  to <code>previous()</code> and the element that would be
 *  returned by a call to <code>next()</code>. In a list of
 *  length <code>n</code>, there are <code>n+1</code> valid
 *  index values, from <code>0</code> to <code>n</code>, inclusive.
 *  <PRE>
 *
 *           Element(0)   Element(1)   Element(2)   ... Element(n)
 *         ^            ^            ^            ^               ^
 *  Index: 0            1            2            3               n+1
 *
 *  </PRE>
 *  <P>
 *  Note that the <A HREF="HListIterator.html#remove()"><CODE>remove()</CODE></A> and <A HREF="HListIterator.html#set(java.lang.Object)"><CODE>set(Object)</CODE></A> methods are
 *  <i>not</i> defined in terms of the cursor position;  they are defined to
 *  operate on the last element returned by a call to <A HREF="HListIterator.html#next()"><CODE>next()</CODE></A> or <A HREF="HListIterator.html#previous()"><CODE>previous()</CODE></A>.
 *  <P>
 */
public interface HListIterator extends HIterator
{
    /**
     * Returns <code>true</code> if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words, returns
     * <code>true</code> if <code>next</code> would return an element rather than
     * throwing an exception.)
     *
     * @return <code>true</code> if the list iterator has more elements when
     *		traversing the list in the forward direction.
     */
    boolean hasNext();

    /**
     * Returns the next element in the list.  This method may be called
     * repeatedly to iterate through the list, or intermixed with calls to
     * <code>previous</code> to go back and forth.  (Note that alternating calls
     * to <code>next</code> and <code>previous</code> will return the same element
     * repeatedly.)
     *
     * @return the next element in the list.
     * @throws NoSuchElementException if the iteration has no next element.
     */
    Object next();

    /**
     * Removes from the list the last element that was returned by
     * <code>next</code> or <code>previous</code> (optional operation).  This call can
     * only be made once per call to <code>next</code> or <code>previous</code>.  It
     * can be made only if <code>ListIterator.add</code> has not been called after
     * the last call to <code>next</code> or <code>previous</code>.
     *
     * @throws UnsupportedOperationException if the <code>remove</code>
     * 		  operation is not supported by this list iterator.
     * @throws IllegalStateException neither <code>next</code> nor
     *		  <code>previous</code> have been called, or <code>remove</code> or
     *		  <code>add</code> have been called after the last call to *
     *		  <code>next</code> or <code>previous</code>.
     */
    void remove();

    /**
     * Returns <code>true</code> if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words, returns
     * <code>true</code> if <code>previous</code> would return an element rather than
     * throwing an exception.)
     *
     * @return <code>true</code> if the list iterator has more elements when
     *	       traversing the list in the reverse direction.
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the list.  This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to <code>next</code> to go back and forth.  (Note that alternating
     * calls to <code>next</code> and <code>previous</code> will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list.
     *
     * @throws NoSuchElementException if the iteration has no previous
     *            element.
     */
    Object previous();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <code>next</code>. (Returns list size if the list iterator is at the
     * end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <code>next</code>, or list size if list iterator is at end
     *	       of list.
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <code>previous</code>. (Returns -1 if the list iterator is at the
     * beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <code>previous</code>, or -1 if list iterator is at
     *	       beginning of list.
     */
    int previousIndex();

    /**
     * Replaces the last element returned by <code>next</code> or
     * <code>previous</code> with the specified element (optional operation).
     * This call can be made only if neither <code>ListIterator.remove</code> nor
     * <code>ListIterator.add</code> have been called after the last call to
     * <code>next</code> or <code>previous</code>.
     *
     * @param o the element with which to replace the last element returned by
     *          <code>next</code> or <code>previous</code>.
     * @throws UnsupportedOperationException if the <code>set</code> operation
     * 		  is not supported by this list iterator.
     * @throws ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     * @throws IllegalArgumentException if some aspect of the specified
     *		  element prevents it from being added to this list.
     * @throws IllegalStateException if neither <code>next</code> nor
     *	          <code>previous</code> have been called, or <code>remove</code> or
     *		  <code>add</code> have been called after the last call to
     * 		  <code>next</code> or <code>previous</code>.
     */
    void set(Object o);

    /**
     * Inserts the specified element into the list (optional operation).  The
     * element is inserted immediately before the next element that would be
     * returned by <code>next</code>, if any, and after the next element that
     * would be returned by <code>previous</code>, if any.  (If the list contains
     * no elements, the new element becomes the sole element on the list.)
     * The new element is inserted before the implicit cursor: a subsequent
     * call to <code>next</code> would be unaffected, and a subsequent call to
     * <code>previous</code> would return the new element.  (This call increases
     * by one the value that would be returned by a call to <code>nextIndex</code>
     * or <code>previousIndex</code>.)
     *
     * @param o the element to insert.
     * @throws UnsupportedOperationException if the <code>add</code> method is
     * 		  not supported by this list iterator.
     *
     * @throws ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     *
     * @throws IllegalArgumentException if some aspect of this element
     *            prevents it from being added to this list.
     */
    void add(Object o);
}
