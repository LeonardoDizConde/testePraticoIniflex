import model.Funcionario;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static model.Utils.formatMonetaryValue;

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
        List<Funcionario> funcionarios = new ArrayList<>();
        HashMap<String, ArrayList<Funcionario>> funcaoFuncionariosMap = new HashMap<>();

        for (String[] itemTabela : dadosTabela) {
            funcionarios.add(Funcionario.convertFromTableItem(itemTabela));
        }

        boolean foundItem = false;
        int findItemIndex = 0;
        while (!foundItem && findItemIndex < funcionarios.size()) {
            Funcionario funcionario = funcionarios.get(findItemIndex);
            if(funcionario.getNome().equals("João")) {
                funcionarios.remove(findItemIndex);
                foundItem = true;
            }
            findItemIndex++;
        }

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }

        for (Funcionario funcionario : funcionarios) {
            funcionario.darAumento(10);
        }

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

        for (Entry<String, ArrayList<Funcionario>> mapEntry : funcaoFuncionariosMap.entrySet()) {
            System.out.printf("- Funcionários com função de %s:\n", mapEntry.getKey());
            for (Funcionario funcionario : mapEntry.getValue()) {
                System.out.println(funcionario.toString());
            }
        }

        for (Funcionario funcionario : funcionarios) {
            Month birthdayMonth = funcionario.getDataNascimento().getMonth();
            if(birthdayMonth == Month.OCTOBER || birthdayMonth == Month.DECEMBER) {
                System.out.println(funcionario.toString());
            }
        }

        Funcionario oldestFuncionario = funcionarios.getFirst();

        for (int indexReq39 = 1; indexReq39 < funcionarios.size(); indexReq39++) {
            Funcionario funcionario = funcionarios.get(indexReq39);
            int oldestFuncionarioBirthYear = oldestFuncionario.getDataNascimento().getYear();
            int funcionarioBirthYear = funcionario.getDataNascimento().getYear();
            if(oldestFuncionarioBirthYear > funcionarioBirthYear) {
                oldestFuncionario = funcionario;
            }
        }

        System.out.println(oldestFuncionario.getNomeIdade());

        printAlphabeticallyFuncionarios(funcionarios);

        BigDecimal sumSalary = new BigDecimal(0);

        for (Funcionario funcionario : funcionarios) {
            sumSalary = sumSalary.add(funcionario.getSalario());
        }

        System.out.println(formatMonetaryValue(sumSalary));

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.calculateMinimumSalaryAmountEarn());
        }
    }

    public static void printAlphabeticallyFuncionarios(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosToOrder = new ArrayList<Funcionario>(funcionarios);

        quicksortFuncionarios(funcionariosToOrder,0, funcionarios.size() - 1);

        for (Funcionario funcionario : funcionariosToOrder) {
            System.out.println(funcionario);
        }
    }


    private static void quicksortFuncionarios(List<Funcionario> funcionarios, int left, int right) {
        int start = left;
        int end = right;

        String pivot = funcionarios.get((start + end) / 2).getNome();

        while (start <= end) {
            while (funcionarios.get(start).getNome().compareTo(pivot) < 0) {
                start++;
            }

            while (funcionarios.get(end).getNome().compareTo(pivot) > 0) {
                end--;
            }

            if (start <= end) {
                Funcionario temp = funcionarios.get(start);
                funcionarios.set(start, funcionarios.get(end));
                funcionarios.set(end, temp);
                start++;
                end--;
            }
        }
        if (left < end) {
            quicksortFuncionarios(funcionarios, left, end);
        }
        if (start < right) {
            quicksortFuncionarios(funcionarios, start, right);
        }
    }
}