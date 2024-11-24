public class Stack<T>//Stack implemented using a singly linked list.
{
    //----------------------Nested Node class
    @SuppressWarnings("hiding")
    private class Node<T>
    {
        private T element;
        private Node<T> next;

        public Node (T element, Node<T> next)//Node constructor.
        {
            this.element = element;
            this.next = next;
        }

        public T getElement() { return element;}
        public void setNext(Node<T> next) {this.next = next;}
        public Node<T> getNext() { return next;}
        @SuppressWarnings("unused")
        public void setElement(T element) { this.element = element;}

    }//---------------End of nested Node class

    private int size = 0;
    private Node<T> topNode = null;

    public Stack(){}//LinkedStack constructor.

    public boolean isEmpty() { return size == 0;}
    public T top() { return topNode.getElement();}
    public int getSize() { return size;}
    public Node<T> getTopNode() {return topNode;}
    //push() method to add new items to our stack.
    public void push(T newElement)
    {
        Node<T> newNode = new Node<>(newElement, topNode);
        if (isEmpty()) newNode.setNext(null);
        topNode = newNode;
        size++;
    }
    
    //pop() method to remove and return the last item of our stack.
    public T pop() 
    {  
        if (isEmpty()){return null;}
        else
        {
            T topElement = topNode.getElement();
            topNode = topNode.getNext();
            size--;
            return topElement;
        }
    }

}

