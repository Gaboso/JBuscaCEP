package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Endereco;
import br.com.gaboso.cep.util.JSoupUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

public class JBuscaCEP {

    private static final Logger LOGGER = LogManager.getLogger(JBuscaCEP.class.getName());

    private JBuscaCEP() {
    }

    public static Endereco getEndereco(String cep) {
        final String cepLimpo = limparCEP(cep);

        if (null == cepLimpo || cepLimpo.length() != 8) {
            LOGGER.error("CEP inv\u00E1lido");
            return new Endereco();
        }

        Endereco endereco = new Endereco(cepLimpo);

        try {
            JSoupUtils jSoupUtils = new JSoupUtils(cepLimpo);

            if (jSoupUtils.isEnderecoInvalido()) {
                endereco.setCep(null);
                return endereco;
            }

            endereco.setRua(jSoupUtils.getRua());
            endereco.setBairro(jSoupUtils.getBairro());
            endereco.setCidade(jSoupUtils.getCidade());
            endereco.setEstado(jSoupUtils.getEstado());

            String latLong = jSoupUtils.getLatLong();
            if (isValidoLatLong(latLong)) {
                String[] latLongArray = latLong.split(" / ");
                endereco.setLatitude(getNumero(latLongArray[0]));
                endereco.setLongitude(getNumero(latLongArray[1]));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return endereco;
    }

    private static Float getNumero(String texto) {
        float numero = 0F;
        String[] textoArray = texto.split(": ");

        if (textoArray.length >= 2) {
            String valor = textoArray[1];

            try {
                numero = Float.parseFloat(valor);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return numero;
    }

    private static String limparCEP(String cep) {
        if (Strings.isBlank(cep)) {
            return null;
        }

        return cep.replaceAll("[^0-9]", "");
    }

    private static boolean isValidoLatLong(String latLong) {
        if (latLong != null) {
            String[] latLongArray = latLong.split(" / ");
            return latLongArray.length >= 2;
        }

        return false;
    }

}
