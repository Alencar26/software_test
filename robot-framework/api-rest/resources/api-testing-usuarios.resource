*** Settings ***
Library    RequestsLibrary
Library    String

*** Keywords ***
Criar novo usuário
    ${palavra_aleatoria}    Generate Random String    length=4  chars=[LETTERS]
    ${palavra_aleatoria}    convert to lower case    ${palavra_aleatoria}
    Set Test Variable       ${EMAIL}   ${palavra_aleatoria}@gmail.com
    Log To Console          ${EMAIL}