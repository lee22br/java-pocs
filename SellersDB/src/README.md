
##Para subir o banco:
docker-compose up -d


##Para verificar se o container está rodando:
docker ps


##Para acessar o banco via linha de comando (dentro do container):
docker exec -it sales_db_container psql -U admin -d sales_management

##Para parar tudo
docker-compose down

