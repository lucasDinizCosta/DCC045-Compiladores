echo "----  LIMPA, CONSTROI E COMPILA TODO O PROJETO(PROCESSO COMPLETO)----"
cd lang
cd parser
echo "----  Limpando o projeto"
rm .antlr\* 
rm .antlr
rm *.interp
rm *.tokens
rm *.class
rm LangLexer.*
rm LangParser.*
rm LangBaseListener.*
rm LangListener.*
echo "----  Gera as classes java do analisador sintatico"
java -jar ../../lib/ANTLR.jar Lang.g4
cd ..
cd ast
rm *.class
echo "----  Compila as classes do AST"
javac -cp ../lib/ANTLR.jar:.. *.java
cd ..
echo "----  Compila as classes do PARSER"
javac -cp ../lib/ANTLR.jar:.. ./parser/*.java
rm *.class
echo "----  Compila a classe LangCompiler"
javac -cp ../lib/ANTLR.jar:.. LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"