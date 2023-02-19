*** Settings ***
Library    SeleniumLibrary
Resource          ./amazon_resources.robot

*** Variables ***
${URL}    http://www.amazon.com.br
${MENU_ELETRONICOS}  //a[@href='/Eletronicos-e-Tecnologia/b/?ie=UTF8&node=16209062011&ref_=nav_cs_electronics'][contains(.,'Eletrônicos')]
${H1_PAGINA_ELETRONICOS}  //h1[contains(.,'Eletrônicos e Tecnologia')]

*** Keywords ***

Abrir navegador
    Open Browser  browser=chrome
    Maximize Browser Window

Fechar navegador
    Capture Page Screenshot
    Close Browser

Dado que estou na home page da Amazon.com.br
    Acessar a home page do site amazon.com.br

Quando acessar o menu "Eletrônicos"
    Entrar no menu "Eletrônicos"

Então o título da página deve ficar "${TITLE}"
    Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Verificar se o título da página fica "${TITLE}"

E o texto "Eletrônicos e Tecnologia" deve ser exibido na página
    Verificar se aparece a frase "Eletrônicos e Tecnologia"

E a categoria "${NOME_CATEGORIA}" deve ser exibida na página
    Verificar se aparece a categoria "${NOME_CATEGORIA}"

Quando pesquisar pelo produto "${NOME_PRODUTO}"
    Digitar o nome de produto "${NOME_PRODUTO}" no campo de pesquisa
    Clicar no botão de pesquisa

E um produto da linha "${NOME_PRODUTO}" deve ser mostrado na página
   Verificar o resultado da pesquisa, se está listando o produto "${NOME_PRODUTO}"