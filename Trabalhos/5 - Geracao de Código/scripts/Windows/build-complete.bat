@ECHO OFF
cls
cd ..
cd ..
echo "----  LIMPA, CONSTROI E COMPILA TODO O PROJETO(PROCESSO COMPLETO)----"
cd lang
cd parser
echo "----  Limpa a pasta de parser"
echo "para o windows em ingles"
echo "y|del .antlr\* "
echo "para o windows em portugues"
echo S | del .antlr\* 
echo S | rmdir .antlr
del *.interp
del *.tokens
del *.class
del LangLexer.*
del LangParser.*
del LangBaseListener.*
del LangListener.*
echo "----  Gera as classes java do interpretador"
java -jar ../../lib/ANTLR.jar -visitor Lang.g4
echo "----  COMPILACAO ------------"
cd ..
cd ..
echo "----  Compila as classes do AST"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/ast/*.java
echo "----  Compila as classes do PARSER"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/parser/*.java
echo "----  Compila as classes do INTERPRETER"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/interpreter/*.java
echo "----  Compila as classes do SEMANTIC"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/semantic/*.java
echo "----  Compila as classes do CODE-GENERATOR"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/codeGenerator/*.java
echo "----  Compila a classe LangCompiler"
javac -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. -d . lang/LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"
pause