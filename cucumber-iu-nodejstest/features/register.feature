
Feature: Registrar Usuario
    Scenario: Abrir REGISTER http://localhost:8185/registration
        Given Abrir REGISTER
        And Ingresar datos de Usuario
        Then Usuario registrado