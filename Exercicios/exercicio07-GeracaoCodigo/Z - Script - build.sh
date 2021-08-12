#!/bin/sh
echo "---- BUILD COMPLETA DO PROJETO ----"
x=`java -jar parsers/beaver-cc-0.9.11.jar -T parsers/MiniLang.grammar`
echo "$x"
x=`java -jar parsers/jflex-full-1.8.2.jar -nobak parsers/MiniLang.jflex`
echo "$x"
x=`javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar -d . ast/*.java`
echo "$x"
x=`javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar -d . langUtil/*.java`
echo "$x"
x=`javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar -d . parsers/*.java`
echo "$x"
x=`javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar -d . visitors/*.java`
echo "$x"
x=`javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar -d . MiniLang.java`
echo "$x"
echo "---- BUILD DO PROJETO CONCLUIDA COM SUCESSO----"