@ECHO OFF
cls
echo "----  EXECUTANDO O CODE-GENERATOR ----"
java -cp lib/ANTLR.jar;lib/ST-4.3.1.jar;. lang/LangCompiler -Java ./meusTestes/input_CodeGenerator.txt
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause