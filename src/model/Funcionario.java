package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
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

    public void darAumento(double porcentagemAumento) {
        BigDecimal aumento = BigDecimal.valueOf(1 + porcentagemAumento / 100);
        this.salario = salario.multiply(aumento);
    }

    public String getSalarioFormated() {
        DecimalFormatSymbols monetarySymbol = new DecimalFormatSymbols();
        monetarySymbol.setDecimalSeparator(',');
        monetarySymbol.setGroupingSeparator('.');
        DecimalFormat monetaryParser = new DecimalFormat("#,###.00", monetarySymbol);
        return monetaryParser.format(salario);
    }

    public String toString() {
        String text = "- Funcionário(a):\n\t- Nome: %s\n\t- Data de nascimento: %s\n\t- Salário: %s\n\t- Função: %s\n";
        String formatedBirth = timeParser.format(this.getDataNascimento());

        return String.format(text, this.getNome(), formatedBirth, getSalarioFormated(), funcao);
    }

    public static Funcionario convertFromTableItem(String[] tableItem) {
        String nome = tableItem[0];
        LocalDate dataNascimento = LocalDate.parse(tableItem[1], timeParser);
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(tableItem[2]));
        String funcao = tableItem[3];
        return new Funcionario(nome, dataNascimento, salario, funcao);
    }
}
