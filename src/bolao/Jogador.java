package bolao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jogador extends Pessoa {
    private String pix;
    Scanner s = new Scanner(System.in);

    Jogador() {  // Construtor da classe Jogador
        super();
        System.out.print("Digite o PIX: ");
        this.pix = s.next();
    }
    Jogador(BufferedReader b){
        super (b);
        try {
            this.pix = b.readLine();
        }
        catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!");
        }
    }
    /* Salva os dados do jogador em um arquivo */
    public void writeArquivo(BufferedWriter b)throws IOException{
        b.write(this.nome + "\n");
        b.write(this.cpf + "\n");
        b.write(this.pix + "\n");
    }
    /* Imprime os dados do jogador */
    public void listarDados() {
        super.listarDados();
        System.out.println("Pix: " + pix);
    }
    public String getCpf() {
        return cpf;
    }
}