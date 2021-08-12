#!/bin/sh
echo "---- EXECUTANDO O PROGRAMA COM O ARQUIVO ----"
x=`java -cp .:libs/beaver-rt-0.9.11.jar:libs/antlr-3.5.2-runtime.jar:libs/ST-4.3.1.jar MiniLang Teste2.txt -Python`
echo "$x"
echo "----------------------------------------------------"
