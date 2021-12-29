package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JBuscaCEPTest {

    @Test
    void getDocument_CepNulo_RetornaEnderecoInvalido() {
        assertAllPropsAreNull(null);
    }

    @Test
    void getDocument_CepMenor_RetornaEnderecoInvalido() {
        assertAllPropsAreNull("12345");
    }

    @Test
    void getDocument_CepMaior_RetornaEnderecoInvalido() {
        assertAllPropsAreNull("1234567-000");
    }

    @Test
    void getDocument_CepSemNumeroInvalido_RetornaEnderecoInvalido() {
        assertAllPropsAreNull("abcd-+_~. []zAQ.,,<; ");
    }

    @Test
    void getDocument_CepNumerosInvalido_RetornaEnderecoInvalido() {
        assertAllPropsAreNull("01234567");
    }

    @Test
    void getDocument_CepValido_RetornaEnderecoValido() {
        Endereco endereco = JBuscaCEP.getEndereco("30672480");

        Assertions.assertEquals("MG", endereco.getEstado());
        Assertions.assertEquals("30672480", endereco.getCep());
        Assertions.assertEquals("Belo Horizonte", endereco.getCidade());
        Assertions.assertEquals(-20.027609F, endereco.getLatitude());
        Assertions.assertEquals(-44.0261F, endereco.getLongitude());
        Assertions.assertEquals("Mineirão", endereco.getBairro());
        Assertions.assertEquals("Rua Alberto Trópia Lopes", endereco.getRua());
    }

    private void assertAllPropsAreNull(String cep) {
        Endereco endereco = JBuscaCEP.getEndereco(cep);

        Assertions.assertNull(endereco.getEstado());
        Assertions.assertNull(endereco.getCep());
        Assertions.assertNull(endereco.getCidade());
        Assertions.assertNull(endereco.getLatitude());
        Assertions.assertNull(endereco.getLongitude());
        Assertions.assertNull(endereco.getBairro());
        Assertions.assertNull(endereco.getRua());
    }

}