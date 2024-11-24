public class DoublyLinkedList<T> 
{   
    //---------------------------------------------------Nested Node class
    @SuppressWarnings("hiding") 
    private class Node<T>
    {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element, Node<T> next, Node<T> prev)
        {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {return this.element;}
        public Node<T> getNext() {return this.next;}
        public Node<T> getPrev() {return this.prev;}

        @SuppressWarnings("unused")
        public void setElement(T element) {this.element = element;}
        public void setNext(Node<T> next) {this.next = next;}
        public void setPrev(Node<T> prev) {this.prev = prev;}
    }
    //--------------------------------------------------End of nested Node class


    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    //constructor
    public DoublyLinkedList() {}
    
    //isEmpty() method that checks if the list is empty.
    public boolean isEmpty() {return (size==0);}
    
    //get methods.
    public int getSize() {return size;}
    public Node<T> getHead() {return head;}
    public Node<T> getTail() {return tail;}
    
    public T getFirst()
    {
        if (isEmpty()) return null;

        return head.getElement();
    }

    public T getLast()
    {
        if (isEmpty()) return null;

        return tail.getElement();
    }
    //end of get methods.

    //addFirst() method to add an element to the beginnng of out list.
    public void addFirst(T newElement)
    {
        Node<T> newHead = new Node<>(newElement, head, null);
        
        if (this.isEmpty())
        {
            head = newHead;
            tail = head;
            size++;
        }

        else    
        {    
            head = newHead;
            size++;
        }
    }

    //addLast() method to add an element to the end of our list.
    public void addLast(T newElement)
    {
        Node<T> newTail = new Node<>(newElement, null, tail);
        if (this.isEmpty())
        {
            tail = newTail;
            head = tail;
            size++;
        }
        
        else
        {    
            tail = newTail;
            size++;
        }
    }

    //addBetween() method to add an element inbetween two nodes.
    //This method should not be used to add an element to the beginnng or the end of the list(use addFirst and addLast)
    public void addBetween(T newElement, Node<T> predecessor, Node<T> successor)
    {
        Node<T> newNode = new Node<>(newElement, predecessor, successor);

        predecessor.setNext(newNode);
        successor.setPrev(newNode);

        size++;
    }
    
    //removeFirst() method to remove the first element of our list and return it. 
    public T removeFirst()
    {
        if (this.isEmpty())
            return null;

        if (size == 1)
        {
            T element = head.getElement();
            head = null;
            tail = null;
            size--;
            return element;
        }

        T element = head.getElement();
        head = head.getNext();
        head.setPrev(null);
        size--;

        return element;
    }

    //removeLast() method to remove the last element of our list and return it.
    public T removeLast()
    {
        if (this.isEmpty())
            return null;

        if (size == 1)
        {
            T element = head.getElement();
            head = null;
            tail = null;
            size--;
            return element;
        }

        T element = tail.getElement();
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
        return element;
    }

}//End of DoublyLinkedList class