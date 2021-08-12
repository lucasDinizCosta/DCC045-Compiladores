@ECHO OFF
cls
echo "---- BUILD COMPLETA DO PROJETO ----"
java -jar parsers/beaver-cc-0.9.11.jar -T parsers/MiniLang.grammar
java -jar parsers/jflex-full-1.8.2.jar -nobak parsers/MiniLang.jflex
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . ast/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . langUtil/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . parsers/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . visitors/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . MiniLang.java
echo "---- BUILD DO PROJETO CONCLUIDA COM SUCESSO----"
pause