echo
echo Install and Run - Poke API
mvn clean install -Dmaven.test.skip=true
mvn spring-boot:run -Dspring.profiles.active=default &

sleep 3
echo
echo Swagger Applications
echo Poke API -\> http://localhost:8080/swagger-ui/index.html
exit