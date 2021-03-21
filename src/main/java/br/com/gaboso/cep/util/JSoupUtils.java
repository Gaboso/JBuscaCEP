package br.com.gaboso.cep.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupUtils {

    private static final Logger LOGGER = LogManager.getLogger(JSoupUtils.class.getName());

    private JSoupUtils() {
    }

    public static Document getDocument(String cep) {
        try {
            return Jsoup
                .connect("http://www.qualocep.com/busca-cep/" + cep)
                .timeout(3000)
                .get();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    public static String getTexto(Elements elements) {
        if (elements != null && !elements.isEmpty()) {
            Element element = elements.get(0);
            return element.text();
        }

        return null;
    }

}
