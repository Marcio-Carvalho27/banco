import java.util.ArrayList;
import java.util.Queue

public class Cartão {
    private int numero;
    private int agencia;

    private String tipo;
    private double saldo;
    private double limite;
    private ArrayList<Compra> historico_compras;
    private Queue<Double> faturas;

    public Cartão(int numero, int agencia){
        this.numero = numero;
        this.agencia = agencia;

        this.tipo = "simples";
        this.saldo = 0;
        this.limite = 1000;
    }

    public Cartão(int numero, int agencia, double saldo){
        this(numero, agencia);
        this.saldo = saldo;
        this.limite = saldo + 1000;
    }

    public String saque(double valor){
        if (valor < this.saldo) {
            this.saldo -= valor;
            this.historico_compras.add(new Compra("Saque", valor));
            return "saque feito com sucesso";
        }
        return "Saldo insuficiente";
    }

    public void deposito(double valor){
        this.saldo += valor;
        this.historico_compras.add(new Compra("Deposito", valor));
    }

    public String fazer_compra(String nome, double valor){
        if (valor < this.saldo) {
            this.saldo -= valor;
            this.historico_compras.add(new Compra(nome, valor));
            this.cashback(valor);

            return "Compra feita com sucesso";
        }
        return "Saldo insuficiente para compra";
    }

    public double limite_gasto(){
        double limite_gasto = 0;
        for (double valor : this.faturas){
            limite_gasto += valor;
        }
        return limite_gasto;
    }

    public void cashback(double valor){
        if (this.tipo.equals("VIP")) {
            this.saldo += 0.05 * valor;
            this.historico_compras.add(new Compra("Cashback", (valor * 0.05)));
        }
    }

    public String fazer_compra_credito(String nome, double valor, int parcelas) {
        double limite_disponivel = this.limite - this.limite_gasto();

        if (valor < limite_disponivel) {
            cashback(valor);

            for (int i = 0; i < parcelas; i++) {
                this.faturas.offer(valor / parcelas);
            }

            return "Compra feita com sucesso";
        }

        return "Limite insuficiente";
    }
}
