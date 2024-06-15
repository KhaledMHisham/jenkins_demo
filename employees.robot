*** Settings ***
Library           RequestsLibrary

*** Variables ***
${URL}            http://localhost:7997

*** Test Cases ***
Verify Response Code Is 200
    Create Session    mysession    ${URL}
    ${response}=    GET On Session    mysession    /employees
    ${status_code}=  Convert To String    ${response.status_code}
    Log To Console    ${status_code}
    Should Be Equal As Strings    ${status_code}    200