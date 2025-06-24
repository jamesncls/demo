package Atividade._FBS.demo;

import model.Perfil;
import model.Usuario;
import service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        // Buscar dados atualizados do usuário
        Usuario usuarioAtual = usuarioService.buscarPorId(usuarioLogado.getId()).orElse(usuarioLogado);
        
        // Criar objeto Perfil para o formulário
        Perfil perfil = new Perfil(usuarioAtual.getNome(), usuarioAtual.getEmail(), usuarioAtual.getCpf());
        model.addAttribute("usuario", perfil);
        
        return "perfil";
    }

    @PostMapping("/perfil")
    public String atualizarPerfil(@ModelAttribute Perfil perfil, Model model, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        try {
            // Buscar usuário atual do banco
            Usuario usuarioAtual = usuarioService.buscarPorId(usuarioLogado.getId()).orElse(null);
            
            if (usuarioAtual == null) {
                model.addAttribute("erro", "Usuário não encontrado!");
                model.addAttribute("usuario", perfil);
                return "perfil";
            }
            
            // Verificar se o novo email já existe (se foi alterado)
            if (!usuarioAtual.getEmail().equals(perfil.getEmail()) && 
                usuarioService.existeEmail(perfil.getEmail())) {
                model.addAttribute("erro", "Este email já está sendo usado por outro usuário!");
                model.addAttribute("usuario", perfil);
                return "perfil";
            }
            
            // Verificar se o novo CPF já existe (se foi alterado)
            if (!usuarioAtual.getCpf().equals(perfil.getCpf()) && 
                usuarioService.existeCpf(perfil.getCpf())) {
                model.addAttribute("erro", "Este CPF já está sendo usado por outro usuário!");
                model.addAttribute("usuario", perfil);
                return "perfil";
            }
            
            // Atualizar dados do usuário
            usuarioAtual.setNome(perfil.getNome());
            usuarioAtual.setEmail(perfil.getEmail());
            usuarioAtual.setCpf(perfil.getCpf());
            
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuarioAtual);
            
            // Atualizar sessão
            session.setAttribute("usuarioLogado", usuarioAtualizado);
            
            model.addAttribute("usuario", new Perfil(usuarioAtualizado.getNome(), 
                                                   usuarioAtualizado.getEmail(), 
                                                   usuarioAtualizado.getCpf()));
            model.addAttribute("sucesso", "Perfil atualizado com sucesso!");
            
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar perfil. Tente novamente.");
            model.addAttribute("usuario", perfil);
        }
        
        return "perfil";
    }
}