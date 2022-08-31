package bolao;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    private final ArrayList<Aposta> apostas;
    private final ArrayList<Jogador> jogadores;
    static Scanner s = new Scanner(System.in);

    Sistema() { // Construtor da classe sistema
        jogadores = new ArrayList<>();
        apostas = new ArrayList<>();
        armazenarJogadores();
    }

    /*
     * Adiciona um novo jogador no ArrayList Jogadores utilizando o construtor de da
     * classe Jogador
     */
    public void cadastrarJogador() {
        Jogador jog = new Jogador();
        this.jogadores.add(jog);
        writeJogadores();
    }

    /* Cria uma aposta e a insere no ArrayList apostas */
    public void cadastrarAposta() {
        Aposta a = new Aposta();

        a.inserirOrganizador(this.jogadores);
        a.inserirJogadores(this.jogadores);
        a.inserirNumeros();

        this.apostas.add(a);
    }

    /* Quando chamado exibe opções e captura a escolha do usuário */
    public static int menuInicial() {
        System.out.println("Bem vindo! Escolha uma opção.\n");
        System.out.println("1) Cadastrar jogador");
        System.out.println("2) Cadastrar aposta");
        System.out.println("3) Inserir sorteio e listar vencedores");
        System.out.println("4) Sair");

        return s.nextInt();
    }

    /* Em conjunto com a função menuInicial chama a função/método para cada opção */
    public static void cadastro(Sistema sis) {
        int opcao;

        opcao = menuInicial();
        while (opcao != 4) {
            if (opcao == 1) {
                sis.cadastrarJogador();
            }
            if (opcao == 2) {
                sis.cadastrarAposta();
            }
            if (opcao == 3) {
                sis.inserirSorteio();
            } else {
                System.out.println("Digite uma opção valida!");
            }
            opcao = menuInicial();
        }
    }

    /* Recebe os array com os números sorteados e verifica se a posta e vencedora */
    private ArrayList<Aposta> vencedoras(ArrayList<Integer> nums) {
        ArrayList<Aposta> premiados = new ArrayList<>();

        for (Aposta a : apostas) {
            if (a.vencedora(nums)) {
                premiados.add(a);
            } else {
                System.out.println("Não houveram vencedores :(");
            }
        }
        return premiados;
    }

    /*
     * Insere os números apostados, valor do premio e verifica se aposta e vencedora
     */
    public void inserirSorteio() {
        int n;
        double premio, premioAposta;
        ArrayList<Integer> sorteados = new ArrayList<>();
        
        try {
            System.out.println("Digite os números sorteados: ");
            for (int i = 0; i < 6; i++) {
                n = s.nextInt();
                if (n >= 1 && n <= 60) {
                    sorteados.add(n);
                }
            }
            System.out.println("Digite o valor do prêmio: ");
            premio = s.nextDouble();

            ArrayList<Aposta> premiados = vencedoras(sorteados);
            premioAposta = premio / premiados.size();

            for (Aposta p : premiados) {
                p.listarVencedores(premioAposta);
            }
        } catch (InputMismatchException e) {
            System.err.println("Número invalido!");
            inserirSorteio();
        }       
    }

    public void writeJogadores() {
        try {
            FileWriter f = new FileWriter("jogadores.txt");
            BufferedWriter b = new BufferedWriter(f);

            b.write(this.jogadores.size() + "\n");
            for (Jogador j : this.jogadores) {
                j.writeArquivo(b);
            }
            b.close(); // Fechamento do arquivo
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    /* Guarda todos os jogadores cadastrados em um arquivo */
    public void armazenarJogadores() {
        int qtdJog;

        try {
            FileReader f = new FileReader("jogadores.txt");
            BufferedReader b = new BufferedReader(f);

            qtdJog = Integer.parseInt(b.readLine());
            for (int i = 0; i < qtdJog; i++) {
                Jogador jog = new Jogador(b);
                this.jogadores.add(jog);
            }
            b.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!");
        }
    }
    public static void main(String[] args) {
        Sistema sis = new Sistema();
        cadastro(sis);
    }
}
