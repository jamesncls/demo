package model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String imagem;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    private Double preco;
    
    public ItemPedido() {}
    
    public ItemPedido(Pedido pedido, String nome, String imagem, Integer quantidade, Double preco) {
        this.pedido = pedido;
        this.nome = nome;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    
    public Double getTotal() {
        return quantidade * preco;
    }
}

