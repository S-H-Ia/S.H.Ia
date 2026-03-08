FROM ubuntu:latest
LABEL authors="kani"

FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=mydatabase
ENV MYSQL_USER=myusername
ENV MYSQL_PASSWORD=mypassword

# Expõe a porta padrão do MySQL
EXPOSE 3306

# Comando para iniciar o MySQL
CMD ["mysqld"]