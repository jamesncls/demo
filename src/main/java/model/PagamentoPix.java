package model;

public class PagamentoPix {
    private String codigoPix;
    private String qrCodePath;

    public PagamentoPix() {}

    public PagamentoPix(String codigoPix, String qrCodePath) {
        this.codigoPix = codigoPix;
        this.qrCodePath = qrCodePath;
    }

    public String getCodigoPix() {
        return codigoPix;
    }

    public void setCodigoPix(String codigoPix) {
        this.codigoPix = codigoPix;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }
}