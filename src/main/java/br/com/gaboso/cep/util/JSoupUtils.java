package br.com.gaboso.cep.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupUtils {

    private Document document;

    private static final Logger LOGGER = LogManager.getLogger(JSoupUtils.class.getName());

    private static final String URL_RUA = "span[itemprop=streetAddress]";
    private static final String URL_BAIRRO = "td:gt(1)";
    private static final String URL_LAT_LONG = "h4";
    private static final String URL_CIDADE = "span[itemprop=addressLocality]";
    private static final String URL_ESTADO = "span[itemprop=addressRegion]";

    public JSoupUtils(String cep) {
        try {
            document = Jsoup.connect("https://www.qualocep.com/busca-cep/" + cep)
                            .timeout(3000)
                            .get();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String getTexto(Elements elements) {
        if (elements != null && !elements.isEmpty()) {
            Element element = elements.get(0);
            return element.text();
        }

        return null;
    }

    public String getRua() {
        if (null == document) {
            return null;
        }

        return getTexto(document.select(URL_RUA));
    }

    public String getBairro() {
        if (null == document) {
            return null;
        }

        return getTexto(document.select(URL_BAIRRO));
    }

    public String getCidade() {
        if (null == document) {
            return null;
        }

        return getTexto(document.select(URL_CIDADE));
    }

    public String getEstado() {
        if (null == document) {
            return null;
        }

        return getTexto(document.select(URL_ESTADO));
    }

    public String getLatLong() {
        if (null == document) {
            return null;
        }

        return getTexto(document.select(URL_LAT_LONG));
    }

    public boolean isEnderecoInvalido() {
        if (null == document) {
            return true;
        }

        Elements avisoNenhumEncontrado = document.select("h2");
        String texto = getTexto(avisoNenhumEncontrado);

        return Strings.isNotBlank(texto) && "NENHUM REGISTRO ENCONTRADO".equals(texto);
    }

}
