@ECHO OFF
cls
echo "---- LIMPANDO O REPOSITORIO ----"
del parsers\MiniLangParser.java
del parsers\MiniLangLex.java
del parsers\Terminals.java
del visitors\*.class
del langUtil\*.class
del ast\*.class
del parsers\*.class
del *.class
echo "-----------LIMPEZA CONCLUIDA COM SUCESSO-----------------"
pause