@ECHO OFF
cls
echo "----  LIMPA, CONSTROI E COMPILA TODO O PROJETO(PROCESSO COMPLETO)----"
cd lang
cd parser
echo "----  Limpa a pasta de parser"
echo "para o windows em ingles"
echo "y|del .antlr\* "
echo "para o windows em portugues"
echo S | del .antlr\* 
echo S | rmdir .antlr
del *.interp
del *.tokens
del *.class
del LangLexer.*
del LangParser.*
del LangBaseListener.*
del LangListener.*
cd ..
cd ast
del *.class
echo "----  PROCESSO DE LIMPEZA CONCLUIDA COM SUCCESSO
pause