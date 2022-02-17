package br.com.gaboso.cep;

import br.com.gaboso.cep.model.Endereco;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length < 1) {
            LOGGER.error("CEP n\u00E3o deve ser branco ou nulo");
        } else {
            Endereco buscaCEP = JBuscaCEP.getEndereco(args[0]);
            LOGGER.info(buscaCEP);
        }
    }

}
