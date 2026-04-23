package com.openchapter.openchapter.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "livros")
public class Livro {

    @Id
    private String id;

    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;
}