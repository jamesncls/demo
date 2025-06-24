package service;

import model.CarrinhoItem;
import model.Usuario;
import repository.CarrinhoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    
    @Autowired
    private CarrinhoItemRepository carrinhoItemRepository;
    
    public List<CarrinhoItem> buscarItensCarrinho(Usuario usuario) {
        return carrinhoItemRepository.findByUsuario(usuario);
    }
    
    public CarrinhoItem adicionarItem(Usuario usuario, String nome, String imagem, double preco) {
        Optional<CarrinhoItem> itemExistente = carrinhoItemRepository.findByUsuarioAndNome(usuario, nome);
        
        if (itemExistente.isPresent()) {
            // Se o item já existe, aumenta a quantidade
            CarrinhoItem item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + 1);
            return carrinhoItemRepository.save(item);
        } else {
            // Se não existe, cria um novo item
            CarrinhoItem novoItem = new CarrinhoItem(usuario, nome, imagem, 1, preco);
            return carrinhoItemRepository.save(novoItem);
        }
    }
    
    public void removerItem(Long itemId) {
        carrinhoItemRepository.deleteById(itemId);
    }
    
    public CarrinhoItem atualizarQuantidade(Long itemId, int novaQuantidade) {
        Optional<CarrinhoItem> item = carrinhoItemRepository.findById(itemId);
        if (item.isPresent()) {
            CarrinhoItem carrinhoItem = item.get();
            if (novaQuantidade <= 0) {
                carrinhoItemRepository.delete(carrinhoItem);
                return null;
            } else {
                carrinhoItem.setQuantidade(novaQuantidade);
                return carrinhoItemRepository.save(carrinhoItem);
            }
        }
        return null;
    }
    
    @Transactional
    public void esvaziarCarrinho(Usuario usuario) {
        carrinhoItemRepository.deleteByUsuario(usuario);
    }
    
    public double calcularTotal(Usuario usuario) {
        List<CarrinhoItem> itens = buscarItensCarrinho(usuario);
        return itens.stream().mapToDouble(CarrinhoItem::getTotal).sum();
    }
    
    public long contarItens(Usuario usuario) {
        return carrinhoItemRepository.countByUsuario(usuario);
    }
}

