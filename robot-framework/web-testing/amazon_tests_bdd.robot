*** Settings ***
Documentation     Essa suíte testa o site amazon.com.br
Resource          ./amazon_resources.robot
Test Setup        Abrir navegador
Test Teardown     Fechar navegador

*** Test Cases ***
Caso de teste 1 - Acesso ao menu "Eletrônicos"
    [Documentation]    Esse teste verifica o menu eletronicos do site da amazon.com.br
    ...                E verifica a categoria Computadores e Informática
    [Tags]            menus  categorias
    Dado que estou na home page da Amazon.com.br
    Quando acessar o menu "Eletrônicos"
    Então o título da página deve ficar "Eletrônicos e Tecnologia | Amazon.com.br"
    E o texto "Eletrônicos e Tecnologia" deve ser exibido na página
    E a categoria "Computadores e Informática" deve ser exibida na página

Caso de teste 2 - Pesquisa de um Produto
    [Documentation]    Esse teste verifica a pesquisa de um produto
    [Tags]             busca_produtos  espaco_pelo_menos_duplo_para_outra_tag
    Dado que estou na home page da Amazon.com.br
    Quando pesquisar pelo produto "Xbox Series S"
    Então um produto da linha "Xbox Series S" deve ser mostrado na página