package model;

public class PagamentoCartao {
    private String numeroCartao;
    private String cvv;
    private String nomeTitular;
    private String validadeMes;
    private String validadeAno;

    public PagamentoCartao() {}

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }

    public String getValidadeMes() { return validadeMes; }
    public void setValidadeMes(String validadeMes) { this.validadeMes = validadeMes; }

    public String getValidadeAno() { return validadeAno; }
    public void setValidadeAno(String validadeAno) { this.validadeAno = validadeAno; }
}