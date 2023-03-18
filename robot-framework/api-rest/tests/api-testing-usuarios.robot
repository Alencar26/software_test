*** Settings ***
Resource    ../resources/api-testing-usuarios.resource
*** Variables ***

*** Test Cases ***
CN0001 - Cadastrar novo usuario com sucesso na serveRest
    Criar novo email de usuário
    Cadastrar o usuário criado na serveRest    ${EMAIL_TEST}    201
    Validar se usuário foi cadastrado corretamente

CN0002 - Cadastrar um usuario ja existente
    Criar novo email de usuário
    Cadastrar o usuário criado na serveRest    ${EMAIL_TEST}    201
    Repetir o cadastro do usuário
    Validar se a API não permitiu o cadastro repetido

CN0003 - Consultar os dados de um novo usuario
    Criar novo email de usuário
    Cadastrar o usuário criado na serveRest    ${EMAIL_TEST}    201
    Consultar os dados no novo usuário
    Validar os dados retornados