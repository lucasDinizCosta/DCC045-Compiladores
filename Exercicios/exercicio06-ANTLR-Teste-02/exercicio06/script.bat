@ECHO OFF
cls
del Teste.class
cd parserExerc06
echo "----  Limpa a pasta de parser"
echo "para o windows em ingles"
echo "y|del .antlr\* "
echo "para o windows em portugues"
echo S | del .antlr\* 
echo S | rmdir .antlr
del *.interp
del *.tokens
del *.class
del *.java
echo "----  Gera as classes java do analisador sintatico"
java -jar ../antlr-4.9.2-complete.jar Lang.g4
echo "----  Compila as classes do analisador sintatico ANTLR"
javac -cp ../antlr-4.9.2-complete.jar;. *.java
echo "----  Volta para a raiz do projeto"
cd .. 
echo "----  Compila a classe principal"
javac -cp ./antlr-4.9.2-complete.jar;. Teste.java
echo "----  Executando a classe principal com a entrada \'input.txt\'"
java -cp ./antlr-4.9.2-complete.jar;. Teste input.txt
pause