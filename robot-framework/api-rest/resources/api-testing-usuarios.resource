*** Settings ***
Library    RequestsLibrary
Library    String
Library    Collections

*** Variables ***
${EMAIL_TEST}
${MESSAGE}
${USER_ID}
${RESPONSE_GET}

*** Keywords ***
Criar novo email de usuário
    ${palavra_aleatoria}    Generate Random String    length=4  chars=[LETTERS]
    ${palavra_aleatoria}    convert to lower case    ${palavra_aleatoria}
    Set Test Variable       ${EMAIL_TEST}   ${palavra_aleatoria}@gmail.com
    Log To Console          ${EMAIL_TEST}

Cadastrar o usuário criado na serveRest
    [Arguments]    ${email}  ${status_code}
    ${body}    Create Dictionary  
    ...        nome=Fulano da Silda  
    ...        email=${email}  
    ...        password=teste  
    ...        administrador=true

    Criar Sessão na ServeRest
    ${response}    POST On Session    
    ...            alias=ServeRest    
    ...            url=/usuarios    
    ...            json=${body}    
    ...            expected_status=${status_code}

    IF  ${response.status_code} == 201
        Set Test Variable  ${USER_ID}  ${response.json()["_id"]}
    END

    Set Test Variable  ${MESSAGE}  ${response.json()}
    

Criar Sessão na ServeRest
    ${headers}    Create Dictionary
    ...           accept=application/json
    ...           Content-Type=application/json
    Create Session    alias=ServeRest    url=https://serverest.dev    headers=${headers}

Validar se usuário foi cadastrado corretamente
    Dictionary Should Contain Item    ${MESSAGE}    message    Cadastro realizado com sucesso
    Dictionary Should Contain Key     ${MESSAGE}    _id

Repetir o cadastro do usuário
    Cadastrar o usuário criado na serveRest    ${EMAIL_TEST}  400

Validar se a API não permitiu o cadastro repetido
    Dictionary Should Contain Item    ${MESSAGE}    message    Este email já está sendo usado

Consultar os dados no novo usuário
    ${response_get}    GET On Session    alias=ServeRest    url=/usuarios/${USER_ID}
    Set Test Variable    ${RESPONSE_GET}    ${response_get.json()}

Validar os dados retornados
    Dictionary Should Contain Key     ${RESPONSE_GET}   nome            Fulano da Silda
    Dictionary Should Contain Key     ${RESPONSE_GET}   email           ${EMAIL_TEST}
    Dictionary Should Contain Key     ${RESPONSE_GET}   password        teste
    Dictionary Should Contain Key     ${RESPONSE_GET}   administrador   true
    Dictionary Should Contain Key     ${RESPONSE_GET}   _id             ${USER_ID}
