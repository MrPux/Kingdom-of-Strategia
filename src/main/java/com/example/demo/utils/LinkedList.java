/**
 * This class implements a linked list.
 */
package com.example.demo.utils;

/**
 * <h1>LinkedList</h1>
 * <p>
 * Represents a generic linked list data structure.
 * </p>
 * <p>
 * The LinkedList class implements a singly linked list, which is a linear collection of elements
 * where each element points to the next element in the sequence. This class provides methods
 * for adding elements, getting the size of the list, clearing the list, getting an element at a
 * specific index, and converting the list to a string representation.
 * </p>
 *
 * @param <T> The type of data stored in the linked list.
 */
public class LinkedList<T> {
    // LinkedList Instance variable
    private Node<T> head;
    private int size = 0;

    /**
     * <h1>Node</h1>
     * <p>
     * A Class of Type Node that holds data and a pointer reference.
     * </p>
     * <p>
     * The Node class represents a single element in the linked list. It stores the data and a
     * reference to the next node in the list.
     * </p>
     *
     * @param <T> The type of data stored in the node.
     */
    public class Node<T>
    {
        //Node Instance Variables
        private T data;
        private Node<T> next;

        //---- Getters ---
        /**
         * Returns the data of the node.
         *
         * @return data
         */
        public T getData()
        {
            return this.data;
        }

        /**
         * Returns the reference of the next node.
         *
         * @return Node
         */
        public Node<T> getNext()
        {
            return this.next;
        }

        //------ Setters ------
        /**
         * Sets the data of the Node to the given data.
         *
         * @param newData
         */
        public void setData(T newData)
        {
            this.data = newData;
        }

        /**
         * Sets a reference point of a node.
         *
         * @param newNext
         */
        public void setNext(Node<T> newNext)
        {
            this.next = newNext;
        }
    }

    /**
     * <h1>add Method</h1>
     * <p>
     * Inserts a node and links it to the chain of nodes.
     * </p>
     * <p>
     * This method adds a new node to the end of the linked list.
     * </p>
     *
     * @param data The data to store in the new node.
     */
    public void add(T data)
    {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);

        if(head == null)
        {
            head = node;
            size++;
        }
        else
        {
            Node<T> tempNode = head;
            while(tempNode.getNext() != null)
            {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(node);
            size++;
        }
    }

    /**
     * <h1>size Method</h1>
     * <p>
     * Returns the size of how many Nodes are in the LinkedList.
     * </p>
     *
     * @return size
     */
    public int size()
    {
        return this.size;
    }

    /**
     * <h1>clear Method</h1>
     * <p>
     * Clears the linked list by removing all nodes.
     * </p>
     */
    public void clear()
    {
        this.head = null;
        size = 0;
    }

    /**
     * <h1>get Method</h1>
     * <p>
     * Gets the data at the specified index in the linked list.
     * </p>
     *
     * @param index The index of the element to retrieve.
     * @return The data at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public T get(int index)
    {
        Node<T> tempNode = head;
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

    /**
     * <h1>toString Method</h1>
     * <p>
     * Returns a string representation of the linked list.
     * </p>
     *
     * @return A string representation of the linked list.
     */
    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        Node<T> tempNode = head;
        while(tempNode != null)
        {
            data.append(" -> ").append(tempNode.getData());
            tempNode = tempNode.getNext();
        }
        return data.toString();
    }
}
