package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa{
    private static final DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private BigDecimal salario;
    private String funcao;
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public static Funcionario convertFromTableItem(String[] tableItem) {
        String nome = tableItem[0];
        LocalDate dataNascimento = LocalDate.parse(tableItem[1], timeParser);
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(tableItem[2]));
        String funcao = tableItem[3];
        return new Funcionario(nome, dataNascimento, salario, funcao);
    }
}
