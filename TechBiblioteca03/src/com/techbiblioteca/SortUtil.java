package com.techbiblioteca;

public class SortUtil {
    // Algoritmo Bubble Sort para ordenar livros
    public static void bubbleSort(Book[] books, boolean sortByTitle) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareBooks(books[j], books[j + 1], sortByTitle) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
    // Algoritmo Quick Sort para ordenar livros
    public static void quickSort(Book[] books, int low, int high, boolean sortByTitle) {
        if (low < high) {
            int pi = partition(books, low, high, sortByTitle);
            quickSort(books, low, pi - 1, sortByTitle);
            quickSort(books, pi + 1, high, sortByTitle);
        }
    }
    private static int partition(Book[] books, int low, int high, boolean sortByTitle) {
        Book pivot = books[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareBooks(books[j], pivot, sortByTitle) < 0) {
                i++;
                Book temp = books[i];
                books[i] = books[j];
                books[j] = temp;
            }
        }
        Book temp = books[i + 1];
        books[i + 1] = books[high];
        books[high] = temp;
        return i + 1;
    }
    private static int compareBooks(Book b1, Book b2, boolean sortByTitle) {
        if (sortByTitle) {
            return b1.getTitle().compareTo(b2.getTitle());
        } else {
            return b1.getAuthor().compareTo(b2.getAuthor());
        }
    }
}
