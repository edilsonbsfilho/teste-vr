# teste-vr
Projeto Criado no Spring Tool Suite 4
Java 11
Spring 2.7.4


Optei por organizar o projeto da seguinte forma/pacotes:

- principal (classe Application)
- config (Classes de configuração)
- controller (Classes que contém os endpoints)
- domain (Classes das Entidades)
- domain.enums (esse pacote está vazio mas eu sempre crio porque geralmente alguma entidade usa pra alguma coluna @Enumerated) 
- dto (Esse padrão eu uso pra trafegar os dados e desacoplar/proteger as informações/entidades, inclusive anoto os atributos para mudar os nomes)
- exception (Classes de Exceptions Especializadas)
- exception.handler (Classe gerenciadora dos exceptions)
- repository (Interfaces de cada dominio)
- service (Classes de negócio. Provedoras de serviços. Responsáveis por manipular seus respectivos DTOs e Entidades relativos ao seu negócio)
- util (outro pacote que gosto de criar por padrão para colocar classes utilitárias que ao longo do ciclo de vida do software vão tendo a necessidade de serem criadas. Conversores, Formatadores, Validadores, etc.)

Para a questão de não usar "IF" escolhi adotar a estratégia de combinar o lançamento de exceção para cada situação de validar ou não uma condição.

Em relação à concorrência, resolvi adotar a anotação @Transactional(isolation = Isolation.READ_COMMITTED), dessa forma o regitro lido por uma transação será o que já foi atualizado por outra transação.
