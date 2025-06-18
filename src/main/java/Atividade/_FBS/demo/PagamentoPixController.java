package Atividade._FBS.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import jakarta.servlet.http.HttpSession;
import model.CarrinhoItem;
import model.PagamentoPix;

@Controller
public class PagamentoPixController {

    @GetMapping("/pagamento-pix")
    public String pagamentoPix(HttpSession session, Model model) throws Exception {
        List<CarrinhoItem> itens = (List<CarrinhoItem>) session.getAttribute("itens");
        if (itens == null) {
            itens = new ArrayList<>();
        }
        double frete = 12.50;
        double total = itens.stream().mapToDouble(i -> i.getPreco() * i.getQuantidade()).sum() + frete;

        String chavePix = "47988503312"; // sua chave Pix
        String nomeRecebedor = "Nycollas James Maviginier";
        String cidade = "Joinville";
        String payload = gerarPayloadPix(chavePix, nomeRecebedor, cidade, total, "Pedido Concept");

        // Gerar QR Code
        String qrCodePath = "/img/qrcode-pix.png";
        String realPath = new File("src/main/resources/static/img/qrcode-pix.png").getAbsolutePath();
        gerarQrCode(payload, realPath);

        model.addAttribute("pagamentoPix", new PagamentoPix(payload, qrCodePath));
        return "pagamento-pix";
    }

    public String gerarPayloadPix(String chavePix, String nomeRecebedor, String cidade, double valor, String infoAdicional) {
        String valorFormatado = String.format("%.2f", valor).replace(",", ".");
        return "000201"
            + "26360014BR.GOV.BCB.PIX0114" + chavePix
            + "52040000"
            + "5303986"
            + "5404" + valorFormatado
            + "5802BR"
            + "590" + String.format("%02d", nomeRecebedor.length()) + nomeRecebedor
            + "600" + String.format("%02d", cidade.length()) + cidade
            + "62070503***";
    }

    public void gerarQrCode(String texto, String caminho) throws Exception {
        int width = 300;
        int height = 300;
        BitMatrix matrix = new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(matrix, "PNG", new File(caminho).toPath());
    }
}