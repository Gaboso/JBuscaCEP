[![Maven Java CI](https://github.com/Gaboso/JBuscaCEP/actions/workflows/maven.yml/badge.svg)](https://github.com/Gaboso/JBuscaCEP/actions/workflows/maven.yml) [![CodeQL](https://github.com/Gaboso/JBuscaCEP/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/Gaboso/JBuscaCEP/actions/workflows/codeql-analysis.yml)

# JBuscaCEP
Buscador de CEP utilizando Java 8 e JSoup 1.15.2

### Como buildar

Execute o __`build.bat`__ para _Windows_ ou __`build.sh`__ para _Linux_, ambos arquivos estão no diretorio raiz deste projeto.

> Depois da execução, o arquivo __`JBuscaCEP-1.3.1.jar`__ será gerado no diretorio `target`

### Exemplos de uso

Importar o `JBuscaCEP` como dependencia e utilizar da seguinte maneira:

```java
String cep = "AQUI_O_CEP_DESEJADO";

Endereco endereco = JBuscaCEP.getEndereco(cep);
```

ou então, depois do build execute o seguinte comando:

```shell
java -jar JBuscaCEP-1.3.1.jar AQUI_O_CEP_DESEJADO
```

### Atributos do endereço:
```java
String cep;
String estado;
String cidade;
String bairro;
String rua;
Float latitude;
Float longitude;
```
