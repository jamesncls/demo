package repository;

import model.CarrinhoItem;
import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
    List<CarrinhoItem> findByUsuario(Usuario usuario);
    Optional<CarrinhoItem> findByUsuarioAndNome(Usuario usuario, String nome);
    void deleteByUsuario(Usuario usuario);
    long countByUsuario(Usuario usuario);
}

