public class CircularSinglyLinkedList<T> 
{
    //Nested Node class
    @SuppressWarnings("hiding")
    private class Node<T> 
    {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next)
        {
            this.element = element;
            this.next = next;
        }

        public T getElement() {return element;}
        public Node<T> getNext() {return next;}
        @SuppressWarnings("unused")
        public void setElement(T element) {this.element = element;}
        public void setNext(Node<T> next) {this.next = next;}
        
    }//End of nested Node class

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    //CircularSinglyLinkedList constructor
    public CircularSinglyLinkedList(){}

    //Getter methods
    public Node<T> getHead() {return head;}
    public T getFirst()      {return head.getElement();}
    public Node<T> getTail() {return tail;}
    public T getLast()       {return tail.getElement();}
    public int getSize()     {return size;}

    //Setter methods
    public void setHead(Node<T> head) {this.head = head;}
    public void setTail(Node<T> tail) {this.tail = tail;}

    public boolean isEmpty() {return this.getSize()==0;}

    public void addFirst(T newElement)
    {
        Node<T> newNode = new Node<>(newElement, head);
        if (isEmpty())
        {
            head = newNode;
            tail = head;
            head.setNext(tail);
            size++;
        }
        else
        {
            head = newNode;
            tail.setNext(head);
            size++;       
        }
    }

    public void addLast(T newElement)
    {
        Node<T> newNode = new Node<>(newElement, head);
        if (isEmpty())
        {
            head = newNode;
            tail = head;
            head.setNext(tail);
            size++;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
            size++;       
        } 
    }

    public T removeFirst()
    {
        if (isEmpty()) return null;

        T element = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty())
        {
            head.setNext(null);
            head = null;
            tail = head;
        }     
        
        return element;

    }

    public T removeLast()
    {
        if (isEmpty()) return null;

        T element = tail.getElement();
        
        Node<T> tempNode = head;

        while(tempNode.getNext()!=tail)
            tempNode = tempNode.getNext();

        tail = tempNode;
        tempNode.setNext(head);
        size--;
        if(isEmpty())
        {
            head = tail = null;
        }

        return element;
    }

    public T getItemAt(int index)
    {
        if (isEmpty()) return null;
        
        if (index+1 > this.getSize() || index < 0) return null;

        Node<T> tempNode = this.head;
        for(int i = 0; i < index; i++)
            tempNode = tempNode.getNext();

        return tempNode.getElement();

    }

    private Node<T> getNodeAt(int index) {
        if (isEmpty()) return null;
        
        if (index+1 > this.getSize() || index < 0) return null;

        Node<T> tempNode = this.head;
        for(int i = 0; i < index; i++)
            tempNode = tempNode.getNext();

        return tempNode;
    }

    public int getIndexOf(T item)
    {
        for (int i = 0; i < this.getSize(); i++)
            if (this.getItemAt(i).equals(item))
                return i;

        return -1;
    }

    public T getNextItem(T item)
    {
        
        if (isEmpty()) return null;

        Node<T> tempNode = this.getHead();

        while (tempNode.getElement() != item) {
            
            tempNode = tempNode.getNext();

        }

        return tempNode.getNext().getElement();
    }

    public T getPreviousItem(T item)
    {
        
        if (isEmpty()) return null;

        Node<T> tempNode = this.getHead();

        while (tempNode.getNext().getElement() != item) {
            
            tempNode = tempNode.getNext();

        }
        
        return tempNode.getElement();
    }

    public void swapItemsByIndex(int i, int j) {

        if (this.isEmpty() || size==1 || i < 0 || j < 0 || i >= size || j >= size ) return;

        T item1 = this.getItemAt(i);
        T item2 = this.getItemAt(j);

        this.getNodeAt(i).setElement(item2);
        this.getNodeAt(j).setElement(item1);

    }

}//End of CircularSinglyLinkedList class
