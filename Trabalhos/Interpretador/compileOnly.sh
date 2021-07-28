echo "----  COMPILACAO ------------"
echo "----  Compila as classes do AST"
javac -cp lib/ANTLR.jar:. -d . lang/ast/*.java
echo "----  Compila as classes do PARSER"
javac -cp lib/ANTLR.jar:. -d . lang/parser/*.java
echo "----  Compila as classes do INTERPRETER"
javac -cp lib/ANTLR.jar:. -d . lang/interpreter/*.java
echo "----  Compila a classe LangCompiler"
javac -cp lib/ANTLR.jar:. -d . lang/LangCompiler.java
echo "----  PROCESSO DE BUILD CONCLUIDO"