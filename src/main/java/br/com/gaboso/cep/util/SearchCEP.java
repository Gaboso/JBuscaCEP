package br.com.gaboso.cep.util;

import br.com.gaboso.cep.model.Cep;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchCEP {

    private static final String URL_RUA = "span[itemprop=streetAddress]";
    private static final String URL_BAIRRO = "td:gt(1)";
    private static final String URL_LAT_LONG = "h4";
    private static final String URL_CIDADE = "span[itemprop=addressLocality]";
    private static final String URL_ESTADO = "span[itemprop=addressRegion]";

    public Cep conexao(String cep) {
        Cep cepEntity = new Cep(cep);

        try {
            Document document = Jsoup.connect("http://www.qualocep.com/busca-cep/" + cep).timeout(800).get();

            cepEntity.setRua(getValue(document.select(URL_RUA)));
            cepEntity.setBairro(getValue(document.select(URL_BAIRRO)));
            cepEntity.setCidade(getValue(document.select(URL_CIDADE)));
            cepEntity.setEstado(getValue(document.select(URL_ESTADO)));

            String latLong = getValue(document.select(URL_LAT_LONG));
            String[] latLongSplited = latLong.split(" / ");
            cepEntity.setLatitude(getNumberFromText(latLongSplited[0]));
            cepEntity.setLongitude(getNumberFromText(latLongSplited[1]));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cepEntity;
    }

    private String getValue(Elements elements) {
        Element element = elements.get(0);
        return element.text();
    }

    private Float getNumberFromText(String text) {
        String[] textSplited = text.split(": ");
        String value = textSplited[1];
        float number = 0F;

        try {
            number = Float.parseFloat(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

}
