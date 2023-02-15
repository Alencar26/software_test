*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${URL}    http://www.amazon.com.br
${MENU_ELETRONICOS}  //a[@href='/Eletronicos-e-Tecnologia/b/?ie=UTF8&node=16209062011&ref_=nav_cs_electronics'][contains(.,'Eletrônicos')]
${H1_PAGINA_ELETRONICOS}  //h1[contains(.,'Eletrônicos e Tecnologia')]
${CATEGORIA_COMPUTADORES_E_INFORMATICA}  //img[@alt='Computadores e Informática']/..

*** Keywords ***

Abrir navegador
    Open Browser  browser=chrome
    Maximize Browser Window

Fechar navegador
    Close Browser

Acessar a home page do site amazon.com.br
    Go To    url=${URL}
    Wait Until Element Is Visible    locator=${MENU_ELETRONICOS}

Entrar no menu "Eletrônicos"
    Click Element    xpath=${MENU_ELETRONICOS}

Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Wait Until Element Is Visible    xpath=${H1_PAGINA_ELETRONICOS}

Verificar se o título da página fica "${TITLE}"
    Title Should Be    title=${TITLE}

Verificar se aparece a categoria "Computadores e Informática"
    Wait Until Element Is Visible    xpath=${CATEGORIA_COMPUTADORES_E_INFORMATICA}