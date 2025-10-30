# clinica-odontologica-api
Sistema de agendamento e gestão odontológica feito com Spring Boot e MySQL.

API RESTful desenvolvida em Java com Spring Boot, aplicando JPA/Hibernate e banco de dados relacional (MySQL).
O sistema gerencia pacientes, dentistas, serviços e agendamentos.

## Tecnologias:
- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Postman

## Funcionalidades:
- CRUD de Pacientes, Dentistas, Serviços e Agendamentos
- Testes de requisição HTTP (GET, POST, PUT, DELETE)
- Validações de dados (como horários e status de agendamentos)
- Relacionamentos entre entidades com JPA

## Banco de Dados
# Tabelas:
- tb_pacientes
- tb_dentistas
- tb_servicos
- tb_agendamentos

## Imagens do Projeto
- Log Spring Boot:

![Log intellij - Spring Boot](imagens/video_log_clinica_odont.mp4)

- Estrutura do Projeto:
![Estrutura do Projeto](imagens/estrutura.png)

- Todas as requisições HTTP no Postman:
![Todas as requisições HTTP no Postman ](imagens/postman_requisicoesPT1.png)
![Todas as requisições HTTP no Postman ](imagens/postman_requisicoesPT2.png)

- Alguns testes no Postman:
![Teste no Postman (GET)](imagens/postmanGET.png)
![Teste no Postman (POST)](imagens/postmanPOST.png)
![Teste no Postman (DELETE)](imagens/postmanDELETE.png)
![Teste no Postman (PUT)](imagens/postmanPUT.png)

- Diagrama das tabelas:
![Diagrama das tabelas](imagens/diagramas.png)

- Dados persistindo no MySQL:
![Dados persistindo no MySQL](imagens/BP_tb_pacientes.png)
![Dados persistindo no MySQL](imagens/BP_tb_servicos.png)
![Dados persistindo no MySQL](imagens/BP_tb_agendamentos.png)
![Dados persistindo no MySQL](imagens/BP_tb_dentistas.png)

