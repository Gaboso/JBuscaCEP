package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Endereco;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import static br.com.gaboso.cep.util.JSoupUtils.getDocument;
import static br.com.gaboso.cep.util.JSoupUtils.getTexto;

public class JBuscaCEP {

    private static final Logger LOGGER = LogManager.getLogger(JBuscaCEP.class.getName());

    private static final String URL_RUA = "span[itemprop=streetAddress]";
    private static final String URL_BAIRRO = "td:gt(1)";
    private static final String URL_LAT_LONG = "h4";
    private static final String URL_CIDADE = "span[itemprop=addressLocality]";
    private static final String URL_ESTADO = "span[itemprop=addressRegion]";

    private JBuscaCEP() {
    }

    public static Endereco getEndereco(String cep) {
        final String cepLimpo = limparCEP(cep);

        if (cepLimpo.length() != 8) {
            LOGGER.error("CEP inválido");
            return new Endereco();
        }

        Endereco endereco = new Endereco(cepLimpo);

        try {
            Document document = getDocument(cepLimpo);

            if (document != null) {
                endereco.setRua(getTexto(document.select(URL_RUA)));
                endereco.setBairro(getTexto(document.select(URL_BAIRRO)));
                endereco.setCidade(getTexto(document.select(URL_CIDADE)));
                endereco.setEstado(getTexto(document.select(URL_ESTADO)));

                String latLong = getTexto(document.select(URL_LAT_LONG));
                if (isValidoLatLong(latLong)) {
                    String[] latLongArray = latLong.split(" / ");
                    endereco.setLatitude(getNumero(latLongArray[0]));
                    endereco.setLongitude(getNumero(latLongArray[1]));
                }

            } else {
                LOGGER.error("Não foi possível carregar o endereço a partir do cep");
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
