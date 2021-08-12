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
echo "----  Gera as classes java do interpretador"
java -jar ../../lib/ANTLR.jar -visitor Lang.g4
echo "\n\n----  COMPILACAO ------------\n\n"
cd ..
cd ..
echo "----  Compila as classes do AST"
javac -cp lib/ANTLR.jar:. -d . lang/ast/*.java
echo "----  Compila as classes do PARSER"
javac -cp lib/ANTLR.jar:. -d . lang/parser/*.java
echo "----  Compila as classes do INTERPRETER"
javac -cp lib/ANTLR.jar:. -d . lang/interpreter/*.java
echo "----  Compila as classes do SEMANTIC"
javac -cp lib/ANTLR.jar:. -d . lang/semantic/*.java
echo "----  Compila a classe LangCompiler"
javac -cp lib/ANTLR.jar:. -d . lang/LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"