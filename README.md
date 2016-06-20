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
Será criado um arquivo `genetic-algorithms-1.0.jar` dentro da pasta `target`. Copie esse arquivo para o a pasta pai:
```bash
cp target/genetic-algorithms-1.0.jar ./genetic-algorithms-1.0.jar
```

### Como realizar uma execução de teste:
Para executar a aplicação, execute o seguinte comando passando o nome do arquivo de saída desejado
e o caminho do arquivo de teste (com os parâmetros) a ser executado:
```bash
java -jar -DtestName=nomeDoTeste genetic-algorithms-1.0.jar --spring.config.location=/caminho/para/application.yml
```
Será gerado como saída:

- O arquivo `results/nomeDoTeste.csv`, com os resultados detalhados do teste
- O arquivo `application.log`, com log interno da aplicação (não relevante para o estudo)

### Como realizar a execução de todos os testes (em batch):
Primeiramente, siga as instruções acima sobre "Como construir o JAR executável do projeto".

Depois, também é necessário compilar e empacotar o módulo de geração e execução de testes:
```bash
cd tests-module
mvn compile package
```
Em seguida, copia-se os JARs gerados para o diretório do módulo de testes:
```bash
cp ../target/genetic-algorithms-1.0.jar ./genetic-algorithms-1.0.jar
cp target/testsmodule-1.0.jar ./testsmodule-1.0.jar
```
Então, pode-se gerar os testes, e em seguida executá-los. A execução dos testes pode demorar e acontece em paralelo
usando todos os núcleos disponíveis no computador atual.
```bash
#Cria os arquivos com parâmetros de testes
java -jar testsmodule-1.0.jar create

#Executa os arquivos de testes criados
java -jar testsmodule-1.0.jar run
```
Será gerado como saída:

- Os arquivos `tests/nomeDoTeste.yml`, com os parâmetros de execução dos testes
- Os arquivos `results/nomeDoTeste.csv`, com os resultados detalhados dos testes
- O arquivo `application.log`, com log interno da aplicação (não relevante para o estudo)