# Приложение StoreManagement

Приложение управление складом, которое формирует приходные расходные накладные по моему замыслу отдача идет в формате JSON где
далее на фронте или, возможно, в другой апишке перерабатывается в нужный формат

P.S. потратил примерно 2,5 дня, к сожалению часть логики не успел, за фронт даже не брался

# Запуск:

-     git clone https://github.com/evgenkolesman/StoreManagement
  при необходимости поднимаем базу данных Postgres в docker-compose там все настроено или свою локальную на нужных
  портах и тд

-     docker-compose up
  Далее собираем
-     mvn clean package

  Далее или через jar

-     java -jar StoreManagement-0.0.1-SNAPSHOT.jar

  или через mvn команды
-     mvn spring-boot:run 

или

-      mvn org.springframework.boot:spring-boot-maven-plugin:run

P.S. при проблемах с портами на Linux легче всего отключить службу постгресс, и заново поднять docker-compose, команда
для отключения службы

-      sudo service postgresql stop