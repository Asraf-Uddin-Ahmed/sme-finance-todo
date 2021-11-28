# sme-finance-todo
TODO List REST API for SME Finance

## Technologies Used
- Java
- Spring Boot
- Hibernate
- MySQL
- Tomcat [Spring Boot Embedded Server]
- Docker

## System configuration prerequisites

First, make sure that the Java 8 JDK, Maven, and MySQL are installed.

## Download the project
- Open the terminal 
- Run command: ```git clone https://github.com/Asraf-Uddin-Ahmed/sme-finance-todo.git```

Before running the clone command please make sure that Git is installed on the PC.

## Build and Run
1. Create a database named *smefinance*
2. Open the terminal
3. Go to *sme-finance-todo* folder where *pom.xml* exists
4. Run ```mvn clean install```
5. Run ```java -jar target\sme-finance-todo-0.0.1-SNAPSHOT.jar```

We can run directly this command: ```mvn spring-boot:run``` , without executing the above. 

## Build and Test
1. Open the terminal
2. Go to *sme-finance-todo* folder where *pom.xml* exists
3. Run ```mvn clean test```

## Dockerize the application
Make sure that Docker is installed on the PC.

### Step 1: Build the project to create executable JAR
Apart from the default profile, there is another profile file named *application-dkr.properties*. This property file is configured for the docker build.

To build the project run following command: 

```mvn clean install -DskipTests -Dspring.profiles.active=dkr```

This command will skip the tests too.

### Step 2: Launch and configure a MySQL docker container
To launch a MySQL docker container run following command:

```docker run -d -p 6666:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=<password>" --env="MYSQL_DATABASE=smefinance" mysql```

It will create and launch a MySQL container named *docker-mysql*. 

To execute SQL file from the host run following command:

```docker exec -i docker-mysql mysql -uuser -ppassword smefinance < data.sql```

Here, just execute the all files from the *migration* folder sequentially:

```docker exec -i docker-mysql mysql -uuser -ppassword smefinance < ".\src\main\resources\db\migration\V1.00__Create_Todo_Task_table.sql"```

```docker exec -i docker-mysql mysql -uuser -ppassword smefinance < ".\src\main\resources\db\migration\V1.01__Alter_collation_of_text_columns_to_utf8_in_Todo_Task_table.sql"```

```docker exec -i docker-mysql mysql -uuser -ppassword smefinance < ".\src\main\resources\db\migration\V1.02__Alter_is_deleted_column__type_to_bit_in_Todo_Task_table.sql"```

To open the bash of *docker-mysql* image: 

```docker exec -it docker-mysql bash```

From here we can connect with MySQL DB and execute MySQL command. So use this for verify that the DB is alright.

### Step 3: Create a docker file
Now, create a docker file named **Dockerfile** in the root folder where *pom.xml* exists. All docker image build and JAR execution commands are stored there.

### Step 4: Build a image
To build a docker image:

```docker build -t smefinancedemo .```

Here, "." is for current directory. To ensuring image creation run following command:

```docker image ls```

It will show the list of all images.

### Step 5: Run docker container
Generally to run the docker container following command is work:

```docker run -p8080:8080 smefinancedemo```

Since we have a MySQL container, we need to link it to the application container.

```docker run -t --link docker-mysql:mysql -p8080:8080 smefinancedemo```

Now 8080 port is open to serve our application from the docker container.

Thanks