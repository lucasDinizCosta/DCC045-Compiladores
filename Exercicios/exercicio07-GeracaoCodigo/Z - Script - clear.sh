echo "---- LIMPANDO O REPOSITORIO ----"
x=`rm -f ./parsers/MiniLangParser.java`
echo "$x"
x=`rm -f ./parsers/MiniLangLex.java`
echo "$x"
x=`rm -f ./parsers/Terminals.java`
echo "$x"
x=`rm -f ./parsers/*.class`
echo "$x"
x=`rm visitors/*.class`
echo "$x"
x=`rm langUtil/*.class`
echo "$x"
x=`rm ast/*.class`
echo "$x"
x=`rm *.class`
echo "$x"
echo "-----------LIMPEZA CONCLUIDA COM SUCESSO-----------------"