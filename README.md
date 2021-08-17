# JBuscaCEP
Buscador de CEP utilizando Java 8 e JSoup 1.14.2

Exemplo de uso:

```java
String cep = "79081-290";

Endereco endereco = JBuscaCEP.getEndereco(cep);
```

Atributos do endereço:
```java
String cep;
String estado;
String cidade;
String bairro;
String rua;
Float latitude;
Float longitude;
```
