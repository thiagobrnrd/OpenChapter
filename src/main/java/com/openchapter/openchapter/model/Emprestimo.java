package com.openchapter.openchapter.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@Document(collection = "emprestimos")
public class Emprestimo {

    @Id
    private String id;

    private String livroId;
    private String livroTitulo;
    private String usuarioEmail;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private String status;
}