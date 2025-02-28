# Documentação Arquitetural

Este diretório consolida toda a documentação relevante do projeto, organizada para facilitar a compreensão e o acompanhamento das decisões arquitetônicas, bem como da estrutura e dos principais componentes. Cada seção aborda um aspecto específico do projeto, desde decisões de design críticas até representações visuais que suportam a arquitetura.

---

## Architecture Haiku

A pasta `architecture-haiku` fornece uma visão concisa dos aspectos arquitetônicos do projeto, encapsulando a essência da arquitetura de maneira breve e reflexiva.

## Architectural Decision Records (ADRs)

A pasta `architectural-decision-records` documenta as principais decisões arquitetônicas tomadas ao longo do projeto, detalhando o contexto, as motivações e as consequências de cada escolha. As decisões estão organizadas da seguinte forma:

| ID      | Título                       | Status | Contexto e Decisão                                                                                                                                                 |
| ------- | ---------------------------- | ------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| ADR-001 | Uso de Clean Architecture    | Aceito | A arquitetura do sistema segue o padrão MVC (Model-View-Controller), separando as camadas de modelo, visão e controle para melhor organização e manutenção. |
| ADR-002 | Uso de DTOs no Back-end      | Aceito | Para evitar exposição direta das entidades do banco de dados, optou-se por usar DTOs para transferir dados entre camadas.                                          |
| ADR-003 | Persistência com JPA e MySQL | Aceito | O banco de dados utilizado será o MySQL, e a persistência será gerenciada pelo Spring Data JPA.                                                                    |
| ADR-004 | Autenticação via JWT         | Aceito | A autenticação será baseada em tokens JWT, garantindo segurança e flexibilidade no controle de acessos.                                                            |
| ADR-005 | Uso de Angular Material      | Aceito | No front-end, a interface será construída com Angular Material para padronização e melhor experiência do usuário.                                                  |
| ADR-006 | Documentação OpenAPI         | Aceito | A API será documentada automaticamente com OpenAPI via Springdoc para facilitar a integração.                                                                      |



# Diagrams

A pasta `diagrams` contém diversas representações visuais, como fluxogramas e diagramas UML, que ajudam a ilustrar e compreender a estrutura do projeto e as interações entre seus componentes.

---

Manter este documento atualizado é essencial para garantir que todos os stakeholders do projeto possam acompanhar as principais decisões e a evolução da arquitetura. Ele serve como uma referência central para uma comunicação clara e eficiente dentro da equipe.