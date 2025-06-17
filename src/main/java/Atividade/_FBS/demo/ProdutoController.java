package Atividade._FBS.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import model.Produto;

@Controller
public class ProdutoController {
    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Camiseta Preta", "/img/camiseta-preta.png", "Preta", 59.90));
        produtos.add(new Produto("Camiseta Verde", "/img/camiseta-verde.png", "Verde", 59.90));
        produtos.add(new Produto("Camiseta Roxa", "/img/camiseta-roxa.png", "Roxa", 59.90));
        produtos.add(new Produto("Camiseta Azul", "/img/camiseta-azul.png", "Azul", 59.90));
        model.addAttribute("produtos", produtos);

        // Simulação de autenticação (troque pela sua lógica real)
        boolean usuarioLogado = true; // Troque para true se o usuário estiver logado
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "produtos";
    }

    @GetMapping("/produto/{nome}")
    public String detalheProduto(@PathVariable String nome, Model model) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Camiseta Preta", "/img/camiseta-preta.png", "Preta", 59.90));
        produtos.add(new Produto("Camiseta Verde", "/img/camiseta-verde.png", "Verde", 59.90));
        produtos.add(new Produto("Camiseta Roxa", "/img/camiseta-roxa.png", "Roxa", 59.90));
        produtos.add(new Produto("Camiseta Azul", "/img/camiseta-azul.png", "Azul", 59.90));

        Produto produtoSelecionado = produtos.stream()
            .filter(p -> p.getNome().replace(" ", "-").equalsIgnoreCase(nome))
            .findFirst()
            .orElse(null);

        if (produtoSelecionado == null) {
            return "redirect:/produtos";
        }

        model.addAttribute("produto", produtoSelecionado);

        // Simulação de autenticação (troque pela sua lógica real)
        boolean usuarioLogado = true; // Troque para true se o usuário estiver logado
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "produto-detalhe";
    }
}