@ECHO OFF
cls
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
echo "----  Gera as classes java do analisador sintatico"
java -jar ../../lib/ANTLR.jar Lang.g4
cd ..
cd ast
del *.class
echo "----  Compila as classes do AST"
javac -cp ../lib/ANTLR.jar;.. *.java
cd ..
echo "----  Compila as classes do PARSER"
javac -cp ../lib/ANTLR.jar;.. ./parser/*.java
cd ..
del *.class
echo "----  Compila a classe LangCompiler"
javac -cp ../lib/ANTLR.jar;.. LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"
pause