import model.Funcionario;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static utils.Utils.formatMonetaryValue;

public class Principal {
    public static String[][] dadosTabela = {
            // Nome    | Nascimento  | Salário   | Função
            { "Maria"  , "18/10/2000", "2009.44" , "Operador"      },
            { "João"   , "12/05/1990", "2284.38" , "Operador"      },
            { "Caio"   , "02/05/1961", "9836.14" , "Coordenador"   },
            { "Miguel" , "14/10/1988", "19119.88", "Diretor"       },
            { "Alice"  , "05/01/1995", "2234.68" , "Recepcionista" },
            { "Heitor" , "19/11/1999", "1582.72" , "Operador"      },
            { "Arthur" , "31/03/1993", "4071.84" , "Contador"      },
            { "Laura"  , "08/07/1994", "3017.45" , "Gerente"       },
            { "Heloísa", "24/05/2003", "1606.85" , "Eletricista"   },
            { "Helena" , "02/09/1996", "2799.93" , "Gerente"       },
    };

    public static void main(String[] args) {
        List<Funcionario> funcionarios;
        HashMap<String, ArrayList<Funcionario>> funcaoFuncionariosMap;

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        funcionarios = criaListaFuncionarios();

        // 3.2 – Remover o funcionário “João” da lista.
        removeFuncionarioPeloNome(funcionarios, "João");

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        imprimirFuncionarios(funcionarios);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        darAumentoPorcentual(funcionarios, 10);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        funcaoFuncionariosMap = agruparFuncionariosPorFuncao(funcionarios);

        // 3.6 – Imprimir os funcionários, agrupados por função.
        imprimiAgrupagentoFuncionarios(funcaoFuncionariosMap);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        imprimiAniversariantesOutubroDezembro(funcionarios);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        imprimiFuncionarioMaisVelho(funcionarios);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        imprimiFuncionariosOrdemAlfabetica(funcionarios);

        // 3.11 – Imprimir o total dos salários dos funcionários.
        imprimiSomaTodosSalarios(funcionarios);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        imprimiQuantosSalariosMinimosCadaGanha(funcionarios);
    }

    public static List<Funcionario> criaListaFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        for (String[] itemTabela : dadosTabela) {
            funcionarios.add(Funcionario.converteDaTabela(itemTabela));
        }
        return funcionarios;
    }

    public static void removeFuncionarioPeloNome(List<Funcionario> funcionarios, String nome) {
        boolean foundItem = false;
        int findItemIndex = 0;
        while (!foundItem && findItemIndex < funcionarios.size()) {
            Funcionario funcionario = funcionarios.get(findItemIndex);
            if(funcionario.getNome().equals(nome)) {
                funcionarios.remove(findItemIndex);
                foundItem = true;
            }
            findItemIndex++;
        }
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("A lista de funciários e suas informações são:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }
    }

    public static void darAumentoPorcentual(List<Funcionario> funcionarios, float porcentagem) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAjustado = funcionario.calculaAumentoPorcentualSalario(porcentagem);
            funcionario.setSalario(salarioAjustado);
        }
    }

    public static HashMap<String, ArrayList<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        HashMap<String, ArrayList<Funcionario>> funcaoFuncionariosMap = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            ArrayList<Funcionario> mapEntry;
            if (funcaoFuncionariosMap.containsKey(funcao)) {
                mapEntry = funcaoFuncionariosMap.get(funcao);
                mapEntry.add(funcionario);
            } else {
                mapEntry = new ArrayList<>();
                mapEntry.add(funcionario);
                funcaoFuncionariosMap.put(funcao, mapEntry);
            }
        }
        return  funcaoFuncionariosMap;
    }

    public static void imprimiAgrupagentoFuncionarios(HashMap<String, ArrayList<Funcionario>> funcaoFuncionariosMap) {
        System.out.println("Os funcionários por função são:");
        for (Entry<String, ArrayList<Funcionario>> mapEntry : funcaoFuncionariosMap.entrySet()) {
            System.out.printf("- %s: ", mapEntry.getKey());
            ArrayList<Funcionario> funcionarios = mapEntry.getValue();
            StringBuilder listaFuncionarios = new StringBuilder(funcionarios.get(0).getNome());

            for (int index = 1; index < funcionarios.size(); index++) {
                listaFuncionarios.append(String.format(", %s", funcionarios.get(index).getNome()));
            }

            System.out.println(listaFuncionarios + ";");
        }
        System.out.print("\n");
    }

    public static void imprimiAniversariantesOutubroDezembro(List<Funcionario> funcionarios) {
        System.out.println("Os funcionários que fazem aniversário no mês 10 ou 12) são: ");
        for (Funcionario funcionario : funcionarios) {
            Month birthdayMonth = funcionario.getDataNascimento().getMonth();
            if(birthdayMonth == Month.OCTOBER || birthdayMonth == Month.DECEMBER) {
                System.out.printf("- %s (no mês %d);\n",funcionario.getNome(), birthdayMonth.getValue());
            }
        }
    }

    public static void imprimiFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario oldestFuncionario = funcionarios.get(0);

        for (int index = 1; index < funcionarios.size(); index++) {
            Funcionario funcionario = funcionarios.get(index);
            int oldestFuncionarioBirthYear = oldestFuncionario.getDataNascimento().getYear();
            int funcionarioBirthYear = funcionario.getDataNascimento().getYear();
            if(oldestFuncionarioBirthYear > funcionarioBirthYear) {
                oldestFuncionario = funcionario;
            }
        }
        System.out.printf("O funcionário mais velho é:\n %s\n", oldestFuncionario.getNomeIdade());
    }

    public static void imprimiFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosToOrder = new ArrayList<>(funcionarios);

        Funcionario.ordenaAlfabeticamenteFuncionarios(funcionariosToOrder);

        System.out.println("A lista dos funcionários em ordem alfabética é:");

        for (Funcionario funcionario : funcionariosToOrder) {
            System.out.printf("- %s;\n", funcionario.getNome());
        }
        System.out.print("\n");
    }

    public static void imprimiSomaTodosSalarios(List<Funcionario> funcionarios) {
        BigDecimal sumSalary = new BigDecimal(0);

        for (Funcionario funcionario : funcionarios) {
            sumSalary = sumSalary.add(funcionario.getSalario());
        }

        System.out.printf("A soma de todos os salários é de R$%s\n\n", formatMonetaryValue(sumSalary));
    }

    public static void imprimiQuantosSalariosMinimosCadaGanha(List<Funcionario> funcionarios) {
        System.out.println("A quantidade que cada um ganha de salários minimos é de:");
        for (Funcionario funcionario : funcionarios) {
            double qtdSalariosMinimos = funcionario.calculaQuantosSalariosMinimosGanha();
            System.out.printf("- %s: %f salários minimos\n", funcionario.getNome(), qtdSalariosMinimos);
        }
    }
}