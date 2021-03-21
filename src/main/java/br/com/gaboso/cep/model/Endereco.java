package br.com.gaboso.cep.model;

public class Endereco {

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Float latitude;
    private Float longitude;

    public Endereco() {
    }

    public Endereco(String cep) {
        setCep(cep);
    }

    public String getCep() {
        return cep;
    }

    private void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Endereco{" +
            "cep='" + cep + '\'' +
            ", estado='" + estado + '\'' +
            ", cidade='" + cidade + '\'' +
            ", bairro='" + bairro + '\'' +
            ", rua='" + rua + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
