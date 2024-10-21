import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private int id;
    private ArrayList<Cartão> cartões;

    public Pessoa(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public void adicionar_cartao(Cartão cartao){
        this.cartões.add(cartao);
    }
}
