#!/bin/sh
echo "---- COMPILANDO OS ARQUIVOS ----"
x=`javac -classpath libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar:. -d . ast/*.java`
echo "$x"
x=`javac -classpath libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar: ./langUtil/*.java`
echo "$x"
x=`javac -classpath libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar: ./parsers/*.java`
echo "$x"
x=`javac -classpath libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar:. -d . ast/*.java`
echo "$x"
x=`javac -classpath libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar: MiniLang.java`
echo "$x"
echo "-----------COMPILACAO CONCLUIDA COM SUCESSO-----------------"