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
    Acessar a home page do site amazon.com.br
    Entrar no menu "Eletrônicos"
    Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Verificar se o título da página fica "Eletrônicos e Tecnologia | Amazon.com.br"
    Verificar se aparece a categoria "Computadores e Informática"

# Caso de teste 2 - Pesquisa de um Produto
#     [Documentation]    Esse teste verifica a pesquisa de um produto
#     [Tags]             busca_produtos  espaco_pelo_menos_duplo_para_outra_tag
#     Acessar a home page do site Amazon.com.br
#     Digitar o nome de produto "Xbox Series S" no campo de pesquisa
#     Clicar no botão de pesquisa
#     Verificar o resultado da pesquisa, se está listando o produto pesquisado