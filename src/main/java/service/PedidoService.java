package service;

import model.*;
import repository.PedidoRepository;
import repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private CarrinhoService carrinhoService;
    
    @Transactional
    public Pedido criarPedido(Usuario usuario, Double frete) {
        List<CarrinhoItem> itensCarrinho = carrinhoService.buscarItensCarrinho(usuario);
        
        if (itensCarrinho.isEmpty()) {
            throw new RuntimeException("Carrinho vazio");
        }
        
        Double subtotal = carrinhoService.calcularTotal(usuario);
        Double total = subtotal + frete;
        
        // Criar pedido
        Pedido pedido = new Pedido(usuario, total, frete);
        pedido = pedidoRepository.save(pedido);
        
        // Criar itens do pedido
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (CarrinhoItem item : itensCarrinho) {
            ItemPedido itemPedido = new ItemPedido(
                pedido, 
                item.getNome(), 
                item.getImagem(), 
                item.getQuantidade(), 
                item.getPreco()
            );
            itensPedido.add(itemPedidoRepository.save(itemPedido));
        }
        
        pedido.setItens(itensPedido);
        
        // Esvaziar carrinho
        carrinhoService.esvaziarCarrinho(usuario);
        
        return pedido;
    }
    
    public List<Pedido> buscarPedidosUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuarioOrderByDataPedidoDesc(usuario);
    }
    
    public List<ItemPedido> buscarItensPedido(Pedido pedido) {
        return itemPedidoRepository.findByPedido(pedido);
    }
}

