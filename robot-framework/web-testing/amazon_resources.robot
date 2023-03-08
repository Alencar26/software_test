*** Settings ***
Library    SeleniumLibrary

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

Acessar a home page do site amazon.com.br
    Go To    url=${URL}
    Wait Until Element Is Visible    locator=${MENU_ELETRONICOS}

Entrar no menu "Eletrônicos"
    Click Element    xpath=${MENU_ELETRONICOS}

Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Wait Until Element Is Visible    xpath=${H1_PAGINA_ELETRONICOS}

Verificar se o título da página fica "${TITLE}"
    Title Should Be    title=${TITLE}

Verificar se aparece a categoria "${NOME_CATEGORIA}"
    Element Should Be Visible   xpath=//img[@alt='${NOME_CATEGORIA}']/..

Digitar o nome de produto "${NOME_PRODUTO}" no campo de pesquisa
    Input Text    id=twotabsearchtextbox    text=${NOME_PRODUTO}

Clicar no botão de pesquisa
    Click Element    id=nav-search-submit-button

Verificar o resultado da pesquisa, se está listando o produto "${NOME_PRODUTO}"
    Wait Until Element Is Visible    xpath=//span[contains(.,'RESULTADOS')]
    Element Should Be Visible    xpath=//span[contains(.,'${NOME_PRODUTO}')]

Verificar o resultado da pesquisa se está listando o produto "Console Xbox Series S"
    Set Selenium Implicit Wait  5 seconds
    Verificar o resultado da pesquisa, se está listando o produto "Console Xbox Series S"

Adicionar o produto "${NOME_PRODUTO}" no carrinho
    Click Image    xpath=//img[@alt='${NOME_PRODUTO}']
    Click Element    id=add-to-cart-button

Verificar se o produto "Console Xbox Series S" foi adicionado com sucesso
    Wait Until Element Is Visible    xpath=//div[@id='sw-atc-details-single-container']//span[contains(.,'Adicionado ao carrinho')]
    Element Should Be Visible    xpath=//div[@id='sw-all-product-variations']//span[contains(.,'Xbox Series S')]

Validar página do carrihno de compras da Amazon.com
    Wait Until Element Is Visible    xpath=//h1[contains(.,'Carrinho de compras')]
    Title Should Be    title=Carrinho de compras da Amazon.com

Validar se o item "Console Xbox Series S" está no carrinho de compras
    Element Should Be Visible    xpath=//span[@class='a-truncate-cut'][contains(.,'Console Xbox Series S')]

Remover o produto "Console Xbox Series S" do carrinho
    Click Element    id=sw-gtc
    Set Selenium Implicit Wait  5 seconds
    Validar página do carrihno de compras da Amazon.com
    Validar se o item "Console Xbox Series S" está no carrinho de compras
    Click Element    xpath=//input[@value='Excluir']

Verificar se o carrinho fica vazio
    Validar página do carrihno de compras da Amazon.com
    Set Selenium Implicit Wait  5 seconds
    Element Should Be Visible    xpath=//h1[contains(.,'Seu carrinho de compras da Amazon está vazio.')]