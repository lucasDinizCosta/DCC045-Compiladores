echo "=========> Limpando o Lexer antigo...."
rm Lexer.*
echo "=========> Limpando os Arquivos .class ...."
rm *.class
echo "=========> Executando o JFLEX para criar o analisador lexico da linguagem Lang"
java -jar jflex-full-1.8.2.jar Lang.jflex
echo "=========> Compilando os arquivos java"
javac -cp . Lang.java
echo "=========> Executando o analisador léxico com o arquivo \'input.txt\'"
java Lang input.txt