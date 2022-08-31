package bolao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Aposta {
    private final ArrayList<Integer> numeros;
    private final ArrayList<Jogador> jogadores;
    Scanner s = new Scanner(System.in);
    Jogador organizador;
    
    Aposta(){ // Construtor da classe Aposta
        numeros = new ArrayList<>();
        jogadores = new ArrayList<>();
    }
    /* Recebe um array com os números sorteados e verifica se todos estão nos números apostados. */
    public boolean vencedora(ArrayList<Integer> nums) {
        int cont = 0;

        for (Integer numero : this.numeros) {
            for (int j = 0; j < 6; j++) {
                if (Objects.equals(numero, nums.get(j))) {
                    cont++;
                }
            }
        }
        return cont == 6; // Se o contador for = 6 a aposta e vencedora
    }
    /* Ao ser invocada cria um ArrayList com os números apostados */
    public void inserirNumeros() {
        int q, n;
        
        try {
            System.out.print("Digite a quantidade de números apostados: ");
            q = s.nextInt();
            while ((q < 6) || (q > 15)) { // Força o usuário digitar um número entre 6 e 15.
                System.out.println("Quantidade inválida! Digite novamente: ");
                q = s.nextInt();
            }
            while (this.numeros.size() < q) {
                n = s.nextInt();
                if (n < 1 || n > 60) { // Força o usuário a digitar um número entre 1 e 60
                    System.out.print("Erro digite novamente: ");
                } else {
                    numeros.add(n);
                }
            }
        }
        catch(InputMismatchException e) {
            System.err.println("Número invalido!");
            inserirNumeros();
        }
    }
    /* Lista os dados dos jogadores da aposta e divide o prêmio entre eles. */
    public void listarVencedores(double premioAposta) {
        double premioOrg, premioReal;

        System.out.println("Vencedores: ");
        this.organizador.listarDados(); // Imprime os dados do organizador

        // Calcula o bonus de 10% do organizador e remove o extra do premio total.
        premioOrg = premioAposta / 10;
        premioReal = premioAposta - premioOrg;
        System.out.println("Prêmio: " + (premioOrg + (premioReal / (this.jogadores.size() + 1))));

        for (Jogador j : jogadores) { // Imprime os dados dos demais jogadores
            j.listarDados();
            System.out.println("Prêmio: " + (premioReal / (this.jogadores.size() + 1)));
        }
    }
    /* Utilizada para indicar o organizador da aposta */
    public void inserirOrganizador(ArrayList<Jogador> jogadores) {
        String cpf;
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("CPF do organizador: ");
            cpf = s.nextLine();
        }
        for (Jogador jogador : jogadores) {     // Percorre o array de jogadores um a um
            if (cpf.equals(jogador.getCpf())) { // Verifica se o CPF e igual ao jogador atual
                this.organizador = jogador;
            }
        }
    }
    /* Recebe a lista de jogadores cadastrados e utiliza o CPF para filtrar e inserir na aposta */
    public void inserirJogadores(ArrayList<Jogador> jogadores) {
        int j;
        String jogadorCpf;
        
        try {
            System.out.println("Número de participantes (Organizador não incluso): ");
            j = s.nextInt();
            for (int i = 0; i <= j; i++) {
                for (Jogador invJogadores: jogadores) {
                    invJogadores.listarDados();
                }
                System.out.println("CPF do jogador: ");
                jogadorCpf = s.nextLine();

                for (Jogador cpfCopia: jogadores) {
                    if (jogadorCpf.equals(cpfCopia.cpf)) {
                        this.jogadores.add(cpfCopia);
                    }
                }
            }
        }
        catch(InputMismatchException e) {
            System.err.println("Número invalido!");
            inserirJogadores(jogadores);
        }
    }
}