*** Settings ***
Resource    ../resources/api-testing-usuarios.resource
*** Variables ***

*** Test Cases ***
CN0001 - Cadastrar novo usuario com sucesso na serveRest
    Criar novo usuário
    # Cadastrar o usuário criado na serveRest
    # Validar se usuário foi cadastrado corretamente