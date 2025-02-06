package model;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNomeIdade() {
        String text = "- Nome: %s\n- Idade: %d anos";
        int idade = LocalDate.now().getYear() - dataNascimento.getYear();
        return text.formatted(nome, idade);
    }
}
