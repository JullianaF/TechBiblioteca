package com.techbiblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "livros.txt";

    // Método para salvar lista de livros em um arquivo
    public static void salvarLivros(List<Book> livros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book livro : livros) {
                writer.write(livro.getTitle() + "," + livro.getAuthor() + "," + livro.getYear());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    // Método para carregar livros do arquivo
    public static List<Book> carregarLivros() {
        List<Book> livros = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    String titulo = dados[0];
                    String autor = dados[1];
                    int ano = Integer.parseInt(dados[2]);
                    livros.add(new Book(titulo, autor, ano));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Um novo arquivo será criado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }

        return livros;
    }
}
