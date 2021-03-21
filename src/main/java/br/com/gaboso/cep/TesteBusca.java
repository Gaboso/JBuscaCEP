package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Endereco;

public class TesteBusca {

    public static void main(String[] args) {
        JBuscaCEP jBuscaCEP = new JBuscaCEP();
        String cep = "79081-290";

        Endereco endereco = jBuscaCEP.getEndereco(cep);
        System.out.println(endereco.toString());
    }

}
