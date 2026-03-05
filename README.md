# Test_API
### [ES]
Proyecto API REST en Java

### [EN]
Test REST API with Java

### [ES]
## 👨‍💻 Sobre mí
Full Stack Developer con más de 3 años de experiencia como Jefe de Unidad Departamental de Programación y Sistemas, especializado en la gestión de equipos y desarrollo de soluciones con ASP Clásico. Con conocimientos en desarrollo de aplicaciones Desktop y Web principalmente con C# bajo Visual Studio. Experiencia en la creación de aplicaciones para agilizar procesos administrativos y apoyar la toma de decisiones, enfocado en resultados y coordinación de equipos de desarrollo. Nivel intermedio del idioma inglés.

### [EN]
## 👨‍💻 About Me
Full Stack Developer with over 3 years of experience as Head of the Departmental Programming and Systems Unit, specialized in team management and solution development with ASP Classic. Skilled in building desktop and web applications mainly with C# under Visual Studio. Experienced in creating applications to streamline administrative processes and support decision-making, with a strong focus on results and team coordination. Intermediate level of English.

### [ES]
## 📖 Descripción del proyecto
Este repositorio contiene una API REST desarrollada en Java.  
El objetivo del proyecto es ofrecer una estructura limpia, escalable y mantenible que pueda extenderse para la automatización de procesos administrativos y de negocio.

### [EN]
## 📖 Project Description
This repository contains a REST API developed in Java.  
The goal of this project is to provide a clean, scalable, and maintainable API structure that can be extended for administrative and business process automation.

### [ES]

## 🛠️ Tecnologías utilizadas
- Java 17+
- Spring Boot
- Maven/Gradle
- Principios RESTful
- Git & GitHub para control de versiones

### [EN]
## 🛠️ Technologies Used
- Java 17+
- Spring Boot
- Maven/Gradle
- RESTful API principles
- Git & GitHub for version control

### [ES]

## ⚙️ Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/MemoRdz/Test_API.git

2. - Cambiar a la carpeta del proyecto:
   ```bash
   cd Test_API/test

3. Construir el proyecto:
   ```bash
   mvn clean install

4. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run

### [EN]
## ⚙️ Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/MemoRdz/Test_API.git

2. Navigate to the project folder:
   ```bash
   cd Test_API/test

3. Build the project:
   ```bash
   mvn clean install

4. Run the application:
   ```bash
   mvn spring-boot:run

### [ES]
## 🚀 Uso
Una vez que la aplicación esté en ejecución, los endpoints estarán disponibles en:
   http://localhost:8080/users

Recursos:

   Ordenar por:
   http://localhost:8080/users?sortedBy=[email|id|name|phone|tax_id|created_at]

   Filtrado:
   http://localhost:8080/users?filter=[prop]+[co|eq|sw|ew]+[value]

   "co" --> Contiene
   "eq" --> Igual
   "sw" --> Inicia con
   "ew" --> Termina con

### [EN]
## 🚀 Usage
Once the application is running, you can access the API endpoints at:

   http://localhost:8080/users

Resources:

   Sorted by:
   http://localhost:8080/users?sortedBy=[email|id|name|phone|tax_id|created_at]

   Filtered:
   http://localhost:8080/users?filter=[prop]+[co|eq|sw|ew]+[value]

   "co" --> Contains
   "eq" --> Equals
   "sw" --> Starts with
   "ew" --> Ends with

### [ES]
## 📂 Estructura del proyecto

Test_API/test/
├── src/
│   ├── main/
│   │   ├── java/        # Código fuente
│   │   └── resources/   # Configuraciones
│   └── test/            # Pruebas unitarias
├── pom.xml              # Configuración Maven
├── README.md            # Documentación del proyecto

### [EN]
## 📂 Project Structure

Test_API/test/
├── src/
│   ├── main/
│   │   ├── java/        # Source code
│   │   └── resources/   # Configurations
│   └── test/            # Unit tests
├── pom.xml              # Maven configuration
├── README.md            # Project documentation

### [ES]
### 🤝 Contribuciones
   ¡Las contribuciones son bienvenidas! Por favor, haz un fork del repositorio y envía un pull request con tus mejoras.

### [EN]
## 🤝 Contributing
   Contributions are welcome! Please fork the repository and submit a pull request with your improvements.

### [ES]
## 📄 Licencia
   Este proyecto está bajo la licencia MIT.

### [EN]
## 📄 License
   This project is licensed under the MIT License.