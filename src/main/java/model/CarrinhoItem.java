package model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrinho_itens")
public class CarrinhoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String imagem;
    
    @Column(nullable = false)
    private int quantidade;
    
    @Column(nullable = false)
    private double preco;

    public CarrinhoItem() {}

    public CarrinhoItem(Usuario usuario, String nome, String imagem, int quantidade, double preco) {
        this.usuario = usuario;
        this.nome = nome;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public double getTotal() {
        return quantidade * preco;
    }
}