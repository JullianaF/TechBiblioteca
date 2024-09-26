package com.techbiblioteca;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    Book book;
    TreeNode left;
    TreeNode right;
    List<Book> booksBySameAuthor;

    public TreeNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
        this.booksBySameAuthor = new ArrayList<>();
    }
}
