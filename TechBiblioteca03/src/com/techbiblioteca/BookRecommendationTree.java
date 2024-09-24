package com.techbiblioteca;

import java.util.ArrayList;
import java.util.List;

public class BookRecommendationTree {
    private TreeNode root;

    // Método para inserir um livro na árvore com base no autor
    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private TreeNode insertRec(TreeNode root, Book book) {
        if (root == null) {
            root = new TreeNode(book);
            return root;
        }
        // Comparação com base no autor
        if (book.getAuthor().compareTo(root.book.getAuthor()) < 0) {
            root.left = insertRec(root.left, book);
        } else if (book.getAuthor().compareTo(root.book.getAuthor()) > 0) {
            root.right = insertRec(root.right, book);
        }
        return root;
    }

    // Método para recomendar livros por autor
    public List<Book> recommendBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        searchByAuthor(root, author, result);
        return result;
    }

    private void searchByAuthor(TreeNode node, String author, List<Book> result) {
        if (node == null) {
            return;
        }
        if (node.book.getAuthor().equalsIgnoreCase(author)) {
            result.add(node.book);
        }
        searchByAuthor(node.left, author, result);
        searchByAuthor(node.right, author, result);
    }
}
