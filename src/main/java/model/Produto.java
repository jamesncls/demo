package model;

public class Produto {
    private String nome;
    private String imagem;
    private String cor;
    private double preco;

    public Produto(String nome, String imagem, String cor, double preco) {
        this.nome = nome;
        this.imagem = imagem;
        this.cor = cor;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public String getImagem() { return imagem; }
    public String getCor() { return cor; }
    public double getPreco() { return preco; }
}