package br.com.gaboso.cep.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnderecoTest {

    @Test
    void testToString() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-000");
        endereco.setBairro("Centro");
        endereco.setCidade("Cruzeiro");
        endereco.setEstado("Acre");
        endereco.setRua("Rua um");
        endereco.setLatitude(1F);
        endereco.setLongitude(2F);

        String expected = "{cep=\"12345-000\", estado=\"Acre\", cidade=\"Cruzeiro\", bairro=\"Centro\", rua=\"Rua um\", latitude=1.0, longitude=2.0}";
        String actual = endereco.toString();

        Assertions.assertEquals(expected, actual);
    }

}