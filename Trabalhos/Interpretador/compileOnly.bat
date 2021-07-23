@ECHO OFF
cls
echo "----  LIMPA, CONSTROI E COMPILA TODO O PROJETO(PROCESSO COMPLETO)----"
cd lang
cd parser
echo "----  Gera as classes java do analisador sintatico"
java -jar ../../lib/ANTLR.jar -visitor Lang.g4
echo "----  COMPILACAO ------------"
cd ..
cd ..
echo "----  Compila as classes do AST"
javac -cp ./lib/ANTLR.jar;.. ./lang/interpreter/*.java
pause