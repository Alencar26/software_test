*** Settings ***
Documentation   Exemplo de tipos de variáveis: DICIONARIOS
*** Variable ***
#Tipo Lista (arrays) - separar itens com espaço duplo
&{ZORO}    nome=Roronoa Zoro  funcao=espadachim  habilidade=zoro sola

*** Test Cases ***
CT0001 - Fale do Vice Capitão dos Chapeus de Palha
    [Documentation]    Esse teste imprime o nome do capitão Luffi
    [Tags]             one-piece
    Fale do Zoro



*** Keywords ***
Fale do Zoro
    Log To Console    Vice capitão dos chapeis de palha, seu nome é ${ZORO.nome}, classe: ${ZORO.funcao} e habilitadade suprema: ${ZORO.habilidade}.