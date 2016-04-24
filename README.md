# Trabalho de IA 2016

**Alunos:**<br/>
Kathelyn Lima - NºUSP 6875115 <br/>
Lucas Machado - NºUSP 7137930 <br/>
Richard Santana - NºUSP 7137541 <br/>

### Como construir o JAR executável do projeto:

**Esse passo só é necessário caso não se tenha o arquivo JAR gerado.**

Caso você tenha o Maven instalado na sua máquina, execute o seguinte comando para compilar e empacotar o programa
 em um arquivo JAR executável.
```bash
mvn compile package
```
Será criado um arquivo `genetic-algorithms-1.0.jar` dentro da pasta `target`. Copie esse arquivo para o a pasta pai.

### Como executar o projeto:
Para executar a aplicação, execute o seguinte comando passando o nome do arquivo de saída desejado
e o caminho do arquivo de teste (com os parâmetros) a ser executado:
```bash
java -jar -DtestName=nomeDoTeste genetic-algorithms-1.0.jar --spring.config.location=/caminho/para/application.yml
```