import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

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

    }
}