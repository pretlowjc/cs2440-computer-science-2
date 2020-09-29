package solution;

import util.DoubleNode;

/**
 * A DoubleLinkedSeq is a sequence of double numbers. The sequence can have a
 * special &quot;current element&quot;, which is specified and accessed through
 * four methods that are not available in the IntArrayBag class (start,
 * getCurrent, advance, and isCurrent).
 * 
 * Limitations:
 * 
 * Beyond Integer.MAX_VALUE element, the size method does not work.
 * 
 * @author Justin Pretlow
 * @version April 22, 2020
 */
public class DoubleLinkedSeq implements Cloneable
{

    // your non-static fields go here.
    private DoubleNode head;
    private DoubleNode tail;
    private DoubleNode cursor;
    private DoubleNode precursor;
    private int manyNodes;
    /**
     * Initializes an empty DoubleLinkedSeq.
     * 
     * @postcondition This sequence is empty.
     */
    public DoubleLinkedSeq()
    {
        manyNodes = 0;
        head = null;
        tail = null;
        cursor = null;
        precursor = null;
    }

    /**
     * Adds a new element to this sequence.
     * 
     * @param element
     *            the new element that is being added to this sequence.
     * 
     * @postcondition a new copy of the element has been added to this sequence.
     *                If there was a current element, then this method places
     *                the new element before the current element. If there was
     *                no current element, then this method places the new
     *                element at the front of this sequence. The newly added
     *                element becomes the new current element of this sequence.
     */
    public void addBefore(double element)
    {
        // code for addBefore
        DoubleNode d = new DoubleNode(element);
        if (manyNodes == 0)
        {           
            head = d;
            tail = d;
            cursor = d;
        }
        else if (!isCurrent())
        {
            d.setLink(head);
            head = d;
            cursor = d;
            precursor = null;            
        }
        else if (manyNodes == 1 || cursor.equals(head))
        {
            d.setLink(head);
            head = d;
            cursor = d;       
        }
        else if (manyNodes > 1)
        {            
            d.setLink(cursor);
            precursor.setLink(d);
            cursor = d;
        }
        manyNodes++;
    }

    /**
     * Adds a new element to this sequence.
     * 
     * @param element
     *            the new element that is being added to this sequence.
     * 
     * @postcondition a new copy of the element has been added to this sequence.
     *                If there was a current element, then this method places
     *                the new element after the current element. If there was no
     *                current element, then this method places the new element
     *                at the end of this sequence. The newly added element
     *                becomes the new current element of this sequence.
     */
    public void addAfter(double element)
    {
        // your code here
        DoubleNode d = new DoubleNode(element);
        if (manyNodes == 0)
        {
            head = d;
            tail = d;
            cursor = d;
            manyNodes++;
        }
        else if (!isCurrent())
        {
            d.setLink(tail.getLink());
            tail.setLink(d);
            precursor = tail;
            tail = d;
            cursor = d;
            manyNodes++;
        }
        else if (cursor.equals(tail) || manyNodes == 1)
        {
            d.setLink(cursor.getLink());
            cursor.setLink(d);
            tail = d;
            precursor = cursor;
            cursor = d;
            manyNodes++;
        }
        else if (manyNodes > 1)
        {
            d.setLink(cursor.getLink());
            cursor.setLink(d);
            precursor = cursor;
            cursor = d;
            manyNodes++;
        }       
    }

    /**
     * Places the contents of another sequence at the end of this sequence.
     * 
     * @precondition other must not be null.
     * 
     * @param other
     *            a sequence show contents will be placed at the end of this
     *            sequence.
     * 
     * @postcondition the elements from other have been placed at the end of
     *                this sequence. The current element of this sequence
     *                remains where it was, and other is unchanged.
     * 
     * @throws NullPointerException
     *             if other is null.
     */
    public void addAll(DoubleLinkedSeq other) throws NullPointerException
    {
        // your code here
        if (other == null)
        {
            throw new NullPointerException();
        }
        DoubleNode t = cursor;
        start();
        for (int i = 0; i < manyNodes; i++)
        {
            advance();
        }
        DoubleLinkedSeq temp = new DoubleLinkedSeq();
        for (DoubleNode cur = other.head; cur != null; cur = cur.getLink())
        {
            temp.addAfter(cur.getData());
        }
        for (DoubleNode cur = temp.head; cur != null; cur = cur.getLink())
        {
            addAfter(cur.getData());
        }
        cursor = t;
    }

    /**
     * Move forward so that the current element is now the next element in the
     * sequence.
     * 
     * @precondition isCurrent() returns true.
     * 
     * @postcondition If the current element was already the end element of this
     *                sequence (with nothing after it), then there is no longer
     *                any current element. Otherwise, the new element is the
     *                element immediately after the original current element.
     * 
     * @throws IllegalStateException
     *             if there is not current element.
     */
    public void advance() throws IllegalStateException
    {
        // your code here
        if (isCurrent())
        {
            if (cursor.equals(tail))
            {
                precursor = cursor;
                cursor = null;
            }
            else
            {
                precursor = cursor;
                cursor = cursor.getLink();
            }
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * Creates a copy of this sequence.
     * 
     * @return a copy of this sequence. Subsequent changes to the copy will not
     *         affect the original, nor vice versa.
     * @throws RuntimeException
     *             if this class does not implement Cloneable.
     * 
     */
    public DoubleLinkedSeq clone() throws RuntimeException
    {
        // your code here. see textbook for hints
        // change this return!
        DoubleLinkedSeq result;
        try
        {
            result = (DoubleLinkedSeq) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException();
        }
        DoubleNode[] links = new DoubleNode[4];
        links = cloneHelper(head, cursor, precursor, head, tail);
        result.head = links[0];
        result.tail = links[1];
        result.cursor = links[2];
        result.precursor = links[3];
        return result;
    }
/**
 * Helper method.
 * @param element sdf
 * @param cursor asdf
 * @param precursor asd
 * @param head asdf
 * @param tail asdf
 * @return result asdf
 */
    public static DoubleNode[] cloneHelper(DoubleNode element, 
        DoubleNode cursor, DoubleNode precursor, 
        DoubleNode head, DoubleNode tail)
    {
    
        DoubleNode cloneHead = null;
        DoubleNode cloneTail = null;
        DoubleNode cloneCursor = null;
        DoubleNode clonePrecursor = null;
        DoubleNode[] result = new DoubleNode[4];
      
        if (element == null)
        {
       
            result[0] = null;
            result[1] = null;
            result[2] = null;
            result[3] = null;      
            return result;
                
        }
        cloneHead = new DoubleNode(element.getData());
        cloneTail = cloneHead;
       
        if (cursor == head)
        {
            cloneCursor = head;     
        }
        if (precursor == head)
        {
            clonePrecursor = head;
        }
        for (element = element.getLink(); 
            element != null; 
            element = element.getLink())
        {      
            DoubleNode temp = new DoubleNode(element.getData());
            cloneTail.setLink(temp);
            cloneTail = temp;
          
            if (element == cursor)
            {   
                cloneCursor = temp;
            }
            else if (element == precursor)
            {
                clonePrecursor = temp;
            }
            result[0] = cloneHead;
            result[1] = cloneTail; 
            result[2] = cloneCursor;
            result[3] = clonePrecursor;
        }
       
        return result;
    
    }

    /**
     * Creates a new sequence that contains all the elements from s1 followed by
     * all of the elements from s2.
     * 
     * @precondition neither s1 nor s2 are null.
     * 
     * @param s1
     *            the first of two sequences.
     * @param s2
     *            the second of two sequences.
     * 
     * @return a new sequence that has the elements of s1 followed by the
     *         elements of s2 (with no current element).
     * 
     * @throws NullPointerException
     *             if s1 or s2 are null.
     */
    public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1,
            DoubleLinkedSeq s2) throws NullPointerException
    {
        // your code here.
        // change this return!
        if (s1 == null || s2 == null)
        {
            throw new NullPointerException();
        }
        DoubleLinkedSeq result = new DoubleLinkedSeq();

        for (DoubleNode cur = s1.head; cur != null; cur = cur.getLink())
        {
            result.addAfter(cur.getData());
        }
        for (DoubleNode cur = s2.head; cur != null; cur = cur.getLink())
        {
            result.addAfter(cur.getData());
        }
        result.advance();
        return result;
    }

    /**
     * Returns a copy of the current element in this sequence.
     * 
     * @precondition isCurrent() returns true.
     * 
     * @return the current element of this sequence.
     * 
     * @throws IllegalStateException
     *             if there is no current element.
     */
    public double getCurrent() throws IllegalStateException
    {
        // your code goes here
        // change this return!
        if (isCurrent())
        {
            return cursor.getData();
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * Determines whether this sequence has specified a current element.
     * 
     * @return true if there is a current element, or false otherwise.
     */
    public boolean isCurrent()
    {
        return cursor != null;
    }

    /**
     * Removes the current element from this sequence.
     * 
     * @precondition isCurrent() returns true.
     * 
     * @postcondition The current element has been removed from this sequence,
     *                and the following element (if there is one) is now the new
     *                current element. If there was no following element, then
     *                there is now no current element.
     * 
     * @throws IllegalStateException
     *             if there is no current element.
     */
    public void removeCurrent() throws IllegalStateException
    {
        // your code goes here
        if (!isCurrent())
        {
            throw new IllegalStateException();
        }
        if (manyNodes == 1)
        {
            head = null;
            cursor = null;
            tail = null;
            manyNodes--;
        }
        else if (cursor.equals(tail))
        {
            precursor.setLink(null);
            tail = precursor;
            cursor = null;
            precursor = null;
            manyNodes--;
        }
        else if (cursor.equals(head))
        {
            head = cursor.getLink();
            cursor.setLink(null);
            cursor = head;
            precursor = null;
            manyNodes--;
        }
        else
        {
            precursor.setLink(cursor.getLink());
            cursor = cursor.getLink();
            manyNodes--;
        }
    }

    /**
     * Determines the number of elements in this sequence.
     * 
     * @return the number of elements in this sequence.
     */
    public int size()
    {
        // your code goes here
        return manyNodes;
    }

    /**
     * Sets the current element at the front of this sequence.
     * 
     * @postcondition If this sequence is not empty, the front element of this
     *                sequence is now the current element; otherwise, there is
     *                no current element.
     */
    public void start()
    {
        // your code goes here.
        if (head == null)
        {
            cursor = null;
        }
        cursor = head;
    }

    /**
     * Returns a String representation of this sequence. If the sequence is
     * empty, the method should return &quot;&lt;&gt;&quot;. If the sequence has
     * one item, say 1.1, and that item is not the current item, the method
     * should return &quot;&lt;1.1&gt;&quot;. If the sequence has more than one
     * item, they should be separated by commas, for example: &quot;&lt;1.1,
     * 2.2, 3.3&gt;&quot;. If there exists a current item, then that item should
     * be surrounded by square braces. For example, if the second item is the
     * current item, the method should return: &quot;&lt;1.1, [2.2],
     * 3.3&gt;&quot;.
     * 
     * @return a String representation of this sequence.
     */
    @Override
    public String toString()
    {
        String result = "<";
        for (DoubleNode cur = head; cur != null; cur = cur.getLink())
        {
            if (cur.equals(cursor) && cur.equals(tail))
            {
                result += "[" + cur.getData() + "]";
            }
            else if (cur.equals(cursor))
            {
                result += "[" + cur.getData() + "], ";
            }
            else if (cur.equals(tail))
            {
                result += cur.getData();
            }
            else
            {
                result += cur.getData() + ", ";
            }
        }
        result += ">";
        return result;
    }

    /**
     * Determines if this object is equal to the other object.
     * 
     * @param other
     *            The other object (possibly a DoubleLinkedSequence).
     * @return true if this object is equal to the other object, false
     *         otherwise. Two sequences are equal if they have the same number
     *         of elements, and each corresponding element is equal
     */
    public boolean equals(Object other)
    {
        // your code goes here
        // change this return!
        if (this.manyNodes == ((DoubleLinkedSeq) other).size())
        {
            if (!(this.cursor == null 
                || ((DoubleLinkedSeq) other).cursor == null))
            {
                if (this.getCurrent() != ((DoubleLinkedSeq) other).getCurrent())
                {
                    return false;
                }
            }
            else
            {
                if (this.cursor == null && ((DoubleLinkedSeq) other).cursor 
                    != null || this.cursor != null 
                    && ((DoubleLinkedSeq) other).cursor == null)
                {
                    return false;
                }
            }
            ((DoubleLinkedSeq) other).start();
            for (DoubleNode cur = head; cur != null; cur = cur.getLink())
            {
                if (cur.getData() != ((DoubleLinkedSeq) other).getCurrent())
                {
                    return false;
                }
                ((DoubleLinkedSeq) other).advance();
            }
        }
        else
        {
            return false;
        }
        return true;
    }
}