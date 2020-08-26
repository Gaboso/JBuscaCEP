package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Cep;
import br.com.gaboso.cep.util.SearchCEP;

public class BuscaCEP {

    public static void main(String[] args) {
        SearchCEP search = new SearchCEP();
        String cep = "79081-290";

        Cep cepEntity = search.conexao(cep);

        String msg = cepEntity.getCep() + " " + cepEntity.getBairro() + " " + cepEntity.getCidade()
                + " " + cepEntity.getEstado() + " " + cepEntity.getRua() + " " + cepEntity.getLatitude()
                + " " + cepEntity.getLongitude();

        System.out.println(msg);
    }

}
