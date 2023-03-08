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

Então o título da página deve ficar "Eletrônicos e Tecnologia | Amazon.com.br"
    Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Verificar se o título da página fica "Eletrônicos e Tecnologia | Amazon.com.br"

E o texto "Eletrônicos e Tecnologia" deve ser exibido na página
    Verificar se aparece a frase "Eletrônicos e Tecnologia"
E a categoria "Computadores e Informática" deve ser exibida na página
    Verificar se aparece a categoria "Computadores e Informática"

Quando pesquisar pelo produto "Xbox Series S"
    Digitar o nome de produto "Xbox Series S" no campo de pesquisa
    Clicar no botão de pesquisa
    
Então um produto da linha "Xbox Series S" deve ser mostrado na página
    Verificar o resultado da pesquisa, se está listando o produto "Xbox Series S"
