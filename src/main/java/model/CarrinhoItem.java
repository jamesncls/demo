package model;

public class CarrinhoItem {
    private String nome;
    private String imagem;
    private int quantidade;
    private double preco;

    public CarrinhoItem() {}

    public CarrinhoItem(String nome, String imagem, int quantidade, double preco) {
        this.nome = nome;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}