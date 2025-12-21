# Configurando o Spring Boot no PC

### Terminal (CMD)
Abra o seu terminal e navegue até o diretório onde deseja clonar o projeto. Exemplo:

cd\\

cd projetos_spring (supondo que esteja no Windows e exista o diretório projetos_spring)

### 2 – Clonando o projeto:
No terminal digite: git clone https://github.com/vidalallan/todo-list.git

### 3 – Acesse a pasta clonada. Exemplo
cd todo-list

### 4 – IntelliJ
Com o IntelliJ aberto, clique no botão open e escolha o diretório do seu projeto.

### 5 - Banco de Dados
Crie um banco de dados com o PostgreSQL chamado dbProjeto (caso deseje mudar o nome do banco, usuário e senha, modifique o arquivo application.properties).

### 6 – Execução
Por fim, execute a aplicação para que as entidades sejam criadas no banco e os end-points possam funcionar corretamente.

### 7 - Visualização 
Na URL do navegador digite: http://localhost:8000/task

**Atenção** 

Para executar cada um dos end-points, pode ser utilizado o **Swagger**

http://localhost:8000/swagger-ui/index.html#/

Foi implementado no arquivo application_aws_properties com as configurações para o banco de dados na AWS, mas por padrão o banco utilizado é local.
