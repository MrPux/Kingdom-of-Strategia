package com.example.demo.utils;

public class LinkedList<T>{
    //LinkedList Intance variable
    private Node<T> head;
    private int size = 0;

    /**
     * A Class of Type Node that holds data and a pointer reference.
     */
    public class Node<T>
    {
        //Node Instance Variables
        private T data;
        private Node<T> next;

        //---- Getters ---
        /**
         * Returns the data of the node.
         * @return data
         */
        public T getData()
        {
            return this.data;
        }

        /**
         * Returns the reference of the next node.
         * @return Node
         */
        public Node<T> getNext()
        {
            return this.next;
        }

        //------ Setters ------
        /**
         * Sets the data of the Node to the given data.
         * @param newData
         */
        public void setData(T newData)
        {
            this.data = newData;
        }

        /**
         * Sets a reference point of a node.
         * @param newNext
         */
        public void setNext(Node<T> newNext)
        {
            this.next = newNext;
        }
    }

    /**
     * Inserts a node and links it to the chain of nodes.
     * @param data
     */
    public void add(T data)
    {
        Node<T> node = new Node<T>();
        node.setData(data); 
        node.setNext(null);

        if(head == null)
        {
            this.head.setNext(node);
            size++;
        }
        else
        {    
            Node<T> tempNode = this.head;
            while(tempNode.getNext() != null)
            {
                tempNode.setNext(tempNode.getNext().getNext());
            }
            tempNode.setNext(node);
            this.head = tempNode;
            size++;
        }
    }

    /**
     * Returns the size of how many Nodes are in the LinkedList.
     * @return size
     */
    public int size()
    {
        return this.size;
    }

    public void clear()
    { 
        this.head = null;
    }

    public T get(int index)
    {
        Node<T> tempNode = this.head;
        int currentIndex = 0;
        while(tempNode != null && currentIndex != index)
        {
            tempNode = tempNode.getNext();
            currentIndex++;
        }
        if(tempNode == null)
        {
            throw new IndexOutOfBoundsException("INDEX" + "Out of bounds");
        }
        return tempNode.getData();
    }

    @Override
    public String toString()
    {
        String data = (String) "";
        Node<T> tempNode = this.head;
        while(tempNode.getNext().getNext() != null)
        {
            data = data + " -> " + tempNode.getData();
            tempNode.setNext(tempNode.getNext());
        }
        return data;
    }
}
