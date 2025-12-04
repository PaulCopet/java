# Ejecutar la backend
Correr el archivo BackendApplication.java

## para acceder a la DB
credenciales: 
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* Password: password

# Comandos para ejecutar la backend
lista de estudiantes:
* curl http://localhost:8080/api/students

Agregar estudiante: con ejemplo
* curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Jhan Paul Copete\",\"email\":\"jhancopet@gmail.com\",\"age\":23}"

Editar estudiante: con ejemplo, este va con el id del estudiante
* curl -X PUT http://localhost:8080/api/students/1 -H "Content-Type: application/json" -d "{\"name\":\"Jhan Paul Copete edit\",\"email\":\"jhancopet@gmail.com\",\"age\":23}"
  
Eliminar estudiante: con ejemplo, este va con el id del estudiante
* curl -X DELETE http://localhost:8080/api/students/1



## Comandos para ejecutar el frontend
Instalar dependencias:
* npm install

Iniciar la aplicación:
* npm start
