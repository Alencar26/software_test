*** Settings ***
Documentation   Exemplo de tipos de variáveis: LISTAS
*** Variable ***
#Tipo Lista (arrays) - separar itens com espaço duplo
@{PERSONAGENS_ONE_PIECE}    Luffi  Zoro  Nami  Usopp  Sanji  Brook  Frnak  Robin  Choper  Yamato

*** Test Cases ***
CT0001 - Imprima o nome do Capitão
    [Documentation]    Esse teste imprime o nome do capitão Luffi
    [Tags]             one-piece
    Imprimir o nome do Luffi

CT0002 - Imprima no nome de todos os tripulantes
    [Documentation]    Esse teste imprime o nome de todos os tripulantes
    [Tags]             one-piece
    Imprimir o nome do Luffi
    Imprimir o nome do Zoro
    Imprimir o nome da Nami
    Imprimir o nome do Usopp
    Imprimir o nome do Sanji


*** Keywords ***
Imprimir o nome do Luffi
    Log To Console    O nome do personagem é ${PERSONAGENS_ONE_PIECE[0]}
Imprimir o nome do Zoro
    Log To Console    O nome do personagem é ${PERSONAGENS_ONE_PIECE[1]}
Imprimir o nome da Nami
    Log To Console    O nome do personagem é ${PERSONAGENS_ONE_PIECE[2]}
Imprimir o nome do Usopp
    Log To Console    O nome do personagem é ${PERSONAGENS_ONE_PIECE[3]}
Imprimir o nome do Sanji
    Log To Console    O nome do personagem é ${PERSONAGENS_ONE_PIECE[4]}