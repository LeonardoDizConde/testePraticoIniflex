package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import static model.Utils.*;

public class Funcionario extends Pessoa {
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
        return formatMonetaryValue(this.salario);
    }

    public String toString() {
        String text = "- Funcionário(a):\n\t- Nome: %s\n\t- Data de nascimento: %s\n\t- Salário: %s\n\t- Função: %s\n";
        String formatedBirth = formatLocalDate(this.getDataNascimento());

        return String.format(text, this.getNome(), formatedBirth, getSalarioFormated(), funcao);
    }

    public static Funcionario convertFromTableItem(String[] tableItem) {
        String nome = tableItem[0];
        LocalDate dataNascimento = getLocalDateFromFormatedDateString(tableItem[1]);
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(tableItem[2]));
        String funcao = tableItem[3];
        return new Funcionario(nome, dataNascimento, salario, funcao);
    }
}
