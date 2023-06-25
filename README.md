# Agenda de Usuários

Este é um projeto de de estudo que demonstra uma implementação básica de uma agenda de usuários utilizando a linguagem Java e o framework Spring.

## Descrição

O projeto consiste em uma aplicação de agenda de usuários, onde é possível criar, visualizar, atualizar e excluir usuários.

A estrutura básica do projeto:

- `src/main/java/br/com/agenda`: Contém as classes Java do projeto.
    - `dto`: Contém os objetos DTO (Data Transfer Object) utilizados para transferência de dados entre as camadas.
    - `mapper`: Contém a classe `UsuarioMapper`, responsável por fazer as conversões entre os objetos DTO e a entidade `Usuario`.
    - `model`: Contém a classe `Usuario`, que representa a entidade de usuário.
    - `controller`: Contém as classes controladoras responsáveis por lidar com as requisições HTTP.
    - `repository`: Contém a interface `UsuarioRepository`, que define as operações de acesso a dados para a entidade `Usuario`.
    - `service`: Contém a classe `UsuarioService`, responsável por implementar a lógica de negócio relacionada aos usuários.

## testes  
98% dos metodos testados e 99% das linhas 

  ![image](https://github.com/reduPKR/agenda_springboot/assets/56879793/3fb5d360-d2a5-4218-8043-1ac368d43266)  

## Pré-requisitos

Para executar este projeto, você precisa ter instalado:

- Java JDK (versão 8 ou superior)
- Maven
