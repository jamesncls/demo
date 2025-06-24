package Atividade._FBS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioCadastro;
import service.UsuarioService;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String mostrarCadastro(Model model) {
        model.addAttribute("usuarioCadastro", new UsuarioCadastro());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioCadastro usuarioCadastro, Model model, HttpSession session) {
        try {
            // Verificar se email já existe
            if (usuarioService.existeEmail(usuarioCadastro.getEmail())) {
                model.addAttribute("erro", "Email já cadastrado!");
                model.addAttribute("usuarioCadastro", new UsuarioCadastro());
                return "cadastro";
            }
            
            // Verificar se CPF já existe
            if (usuarioService.existeCpf(usuarioCadastro.getCpf())) {
                model.addAttribute("erro", "CPF já cadastrado!");
                model.addAttribute("usuarioCadastro", new UsuarioCadastro());
                return "cadastro";
            }
            
            // Criar novo usuário
            Usuario novoUsuario = new Usuario(
                usuarioCadastro.getNome(),
                usuarioCadastro.getEmail(),
                usuarioCadastro.getCpf(),
                usuarioCadastro.getSenha()
            );
            
            Usuario usuarioSalvo = usuarioService.salvarUsuario(novoUsuario);
            
            // Fazer login automático após cadastro
            session.setAttribute("usuarioLogado", usuarioSalvo);
            
            return "redirect:/produtos";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar usuário. Tente novamente.");
            model.addAttribute("usuarioCadastro", new UsuarioCadastro());
            return "cadastro";
        }
    }
}