@ECHO OFF
cls
cd ..
cd ..
echo "----  EXECUTANDO O CODE-GENERATOR ----"
java -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. lang/LangCompiler -C++ ./meusTestes/input_CodeGenerator.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause