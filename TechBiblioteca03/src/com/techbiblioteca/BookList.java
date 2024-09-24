package com.techbiblioteca;

public class BookList {
    private Node head;

    private class Node {
        Book book;
        Node next;

        Node(Book book) {
            this.book = book;
        }
    }

    public void addBook(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printBooks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.book);
            current = current.next;
        }
    }

    public Book[] toArray() {
        int size = getSize();
        Book[] books = new Book[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            books[index++] = current.book;
            current = current.next;
        }
        return books;
    }

    public int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}

