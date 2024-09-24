package com.techbiblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TechBibliotecaApp {

    private static List<Book> livros;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Carrega os livros do arquivo
        livros = FileManager.carregarLivros();

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
                    deleteBook(scanner);
                    break;
                case 6:
                    System.out.println("\nObrigado por usar a TechBiblioteca! Todos os livros foram atualizados com sucesso.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        // Salva os livros no arquivo ao fechar o programa
        FileManager.salvarLivros(livros);
    }

    private static void exibirMenu() {
        System.out.println("\n------------ TechBiblioteca ------------");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Listar Livros");
        System.out.println("3. Ordenar Livros por Título");
        System.out.println("4. Ordenar Livros por Autor");
        System.out.println("5. Excluir Livro");
        System.out.println("6. Sair");
        System.out.print("Digite sua escolha: ");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("\nDigite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha após o int

        livros.add(new Book(title, author, year));

        // Salva a lista de livros após adicionar um novo
        FileManager.salvarLivros(livros);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void listBooks() {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
        } else {
            System.out.println("\nLista de Livros:");
            for (Book livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void sortBooksByTitle() {
        Book[] booksArray = livros.toArray(new Book[0]);
        SortUtil.bubbleSort(booksArray, true); // True para ordenar por título
        livros = new ArrayList<>(List.of(booksArray));
        System.out.println("\nLivros ordenados por título.");
        listBooks(); // Mostra a lista após ordenar
    }

    private static void sortBooksByAuthor() {
        Book[] booksArray = livros.toArray(new Book[0]);
        SortUtil.bubbleSort(booksArray, false); // False para ordenar por autor
        livros = new ArrayList<>(List.of(booksArray));
        System.out.println("\nLivros ordenados por autor.");
        listBooks(); // Mostra a lista após ordenar
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("\nDigite o título do livro a ser excluído: ");
        String title = scanner.nextLine();
        livros.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        FileManager.salvarLivros(livros);
        System.out.println("Livro excluído com sucesso, se encontrado.");
    }
}
