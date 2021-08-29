echo "----  COMPILACAO ------------"
cd ..
cd ..
echo "----  Compila as classes do AST"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/ast/*.java
echo "----  Compila as classes do PARSER"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/parser/*.java
echo "----  Compila as classes do INTERPRETER"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/interpreter/*.java
echo "----  Compila as classes do SEMANTIC"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/semantic/*.java
echo "----  Compila as classes do CODE-GENERATOR"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/codeGenerator/*.java
echo "----  Compila a classe LangCompiler"
javac -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. -d . lang/LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"