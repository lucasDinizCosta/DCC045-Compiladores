@ECHO OFF
cls
echo "----  EXECUTANDO O TYPE-CHECK COM BASE NOS TESTES ----./testes/semantica/errado/data0.lan"
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste0.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste1.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste1eMeio.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste2.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste3.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste4.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste5.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste6.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste7.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste8.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste9.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste10.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste11.lan
java -cp lib/ANTLR.jar;. lang/LangCompiler -ti ./testes/semantica/certo/teste12.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"
pause