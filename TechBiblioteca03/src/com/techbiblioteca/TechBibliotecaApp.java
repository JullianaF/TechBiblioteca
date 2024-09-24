package com.techbiblioteca;

import java.util.List;
import java.util.Scanner;

public class TechBibliotecaApp {
    private static List<Book> livros;
    private static BookRecommendationTree recommendationTree;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        livros = FileManager.carregarLivros();
        recommendationTree = new BookRecommendationTree();

        // Adiciona os livros carregados à árvore de recomendação
        for (Book livro : livros) {
            recommendationTree.insert(livro);
        }

        int opcao = 0;
        while (opcao != 6) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha após o int

            switch (opcao) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    sortBooksByTitle();
                    break;
                case 4:
                    sortBooksByAuthor();
                    break;
                case 5:
                    recommendBooksByAuthor(scanner);
                    break;
                case 6:
                    System.out.println("A TechBiblioteca foi atualizada com sucesso! Obrigado por usar o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        FileManager.salvarLivros(livros);
    }

    private static void exibirMenu() {
        System.out.println("------------ TechBiblioteca ------------");
        System.out.println("1. Adicionar Livro.");
        System.out.println("2. Listar Livros.");
        System.out.println("3. Ordenar Livros por Título.");
        System.out.println("4. Ordenar Livros por Autor.");
        System.out.println("5. Recomendar Livros por Autor.");
        System.out.println("6. Sair.");
        System.out.print("\nDigite sua escolha: ");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("\nDigite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha após o int

        Book newBook = new Book(title, author, year);
        livros.add(newBook);
        recommendationTree.insert(newBook); // Insere na árvore de recomendação

        FileManager.salvarLivros(livros);
        System.out.println("\nLivro adicionado com sucesso!\n");
    }

    private static void listBooks() {
        System.out.println("\nLista de Livros:");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.\n");
        } else {
            for (Book livro : livros) {
                System.out.println(livro);
            }
        }
        System.out.println();
    }

    private static void sortBooksByTitle() {
        livros.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
        System.out.println("\nLivros ordenados por título.\n");
    }

    private static void sortBooksByAuthor() {
        livros.sort((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
        System.out.println("\nLivros ordenados por autor.\n");
    }

    private static void recommendBooksByAuthor(Scanner scanner) {
        System.out.print("\nDigite o nome do autor para recomendação: ");
        String author = scanner.nextLine();
        List<Book> recommendations = (List<Book>) recommendationTree.recommendBooksByAuthor(author);
        
        if (recommendations.isEmpty()) {
            System.out.println("\nNenhum livro encontrado para o autor: " + author + "\n");
        } else {
            System.out.println("\nRecomendações de livros para o autor " + author + ":");
            for (Book book : recommendations) {
                System.out.println(book);
            }
            System.out.println();
        }
    }
}
