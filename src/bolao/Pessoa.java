package bolao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Pessoa {
    protected String nome, cpf;
    Scanner s = new Scanner(System.in);

    Pessoa() {  // Construtor da classe Pessoa
        System.out.print("Digite o nome: ");
        this.nome = s.next();
        System.out.print("Digite o CPF: ");
        this.cpf = s.next();
    }

    Pessoa(BufferedReader b){
        try {
            this.nome = b.readLine();
            this.cpf = b.readLine();
        }
        catch (IOException e){
            System.out.println("Erro ao salvar arquivo!");
        }
    }

    /* Imprime os dados da pessoa */
    public void listarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }
}