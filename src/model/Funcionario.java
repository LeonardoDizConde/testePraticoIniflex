package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static utils.Utils.*;

public class Funcionario extends Pessoa {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal(1212);
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

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal calculaAumentoPorcentualSalario(float porcentagemAumento) {
        return salario.multiply(BigDecimal.valueOf(1 + porcentagemAumento / 100));
    }

    public String getSalarioFormatado() {
        return formatMonetaryValue(this.salario);
    }

    public double calculaQuantosSalariosMinimosGanha() {
        return salario.divide(SALARIO_MINIMO, RoundingMode.DOWN).doubleValue();
    }

    public String toString() {
        String text = "- Nome: %s\n\t- Data de nascimento: %s\n\t- Salário: %s\n\t- Função: %s\n";
        String formatedBirth = formatLocalDate(this.getDataNascimento());

        return String.format(text, this.getNome(), formatedBirth, getSalarioFormatado(), funcao);
    }

    public static Funcionario converteDaTabela(String[] tableItem) {
        String nome = tableItem[0];
        LocalDate dataNascimento = getLocalDateFromFormatedDateString(tableItem[1]);
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(tableItem[2]));
        String funcao = tableItem[3];
        return new Funcionario(nome, dataNascimento, salario, funcao);
    }

    private static void ordenaAlfabeticamenteFuncionarios(List<Funcionario> funcionarios, int comeco, int fim) {
        int esquerda = comeco;
        int direita = fim;

        String pivot = funcionarios.get((esquerda + direita) / 2).getNome();

        while (esquerda <= direita) {
            while (funcionarios.get(esquerda).getNome().compareTo(pivot) < 0) {
                esquerda++;
            }

            while (funcionarios.get(direita).getNome().compareTo(pivot) > 0) {
                direita--;
            }

            if (esquerda <= direita) {
                Funcionario temp = funcionarios.get(esquerda);
                funcionarios.set(esquerda, funcionarios.get(direita));
                funcionarios.set(direita, temp);
                esquerda++;
                direita--;
            }
        }
        if (comeco < direita) {
            ordenaAlfabeticamenteFuncionarios(funcionarios, comeco, direita);
        }
        if (esquerda < fim) {
            ordenaAlfabeticamenteFuncionarios(funcionarios, esquerda, fim);
        }
    }

    public static void ordenaAlfabeticamenteFuncionarios(List<Funcionario> funcionarios) {
        ordenaAlfabeticamenteFuncionarios(funcionarios, 0, funcionarios.size() - 1);
    }
}
