# Barksville.shop
Pet Shop Product Information Management

## Introduction

Hello everyone,

This is my first attempt to create more complex spring web application. Barksville.shop was helping me to learning through practice and practice through solving real life problems. 

## Basic information about project
Application was written for my girlfriends Pet Shop to store data about bought and sold products. Daily reports from shop cash register are uploaded to system by registered user and parsed to be stored in MySQL database. Invoices are input manualy by forms due to the lack of possibility to parse them. The whole point for this project is to create quick raports about daily nett income, product availability and quick acces to all invoices.

Technologies:
- MySQL
- Git (obviously :smiley:)
- Java EE 8
- Spring Boot 2.2.4
- Spring Framework 5.1.3
- Spring Security 5.1.3
- Spring Data 2.1.4
- Tomcat
- Lombock
- Apache - PDFBox Library
- JSP
- HTML

I use MySQL Workbench to set up database, Intellij IDEA Ultimate to write code, and git Bash to maintain github repository.

[Return to top](#Barksville.shop)
## Database Structure

I tried to create flexible database that will allow to quickly acces all requested data. In this section i focused on created tables and their relations.

[More info](Sections/Section%201/DB%20schema.md)

[Return to top](#Barksville.shop)
## Functionality description
I chose and described four core feature of application that provides basic functionality of the project.

### Shop day reports

Shop reports are genereted in daily bases by shop cash register at the and of working day. Raports are in .pdf format and example can be found [here](Sections/Section%202/20-09-2019.pdf). Files are parsed with use of Apache PDFBox Library to create DTO objects before saving items to database.

[More info](Sections/Section%202/Shop%20Reports.md)

[Return to top](#Barksville.shop)

### Invoice creator

This is the only way to add items to the system. Users with proper role will fill in all the details about invoice by set of forms. When everything is done correctly in the last step user can upload .pdf scan of orginal paper and save all to database.

[More info](Sections/Section%202/Invoice%20Creator.md)

[Return to top](#Barksville.shop)

### Financial report generator

With data included in invoices and shop day reports, user can generate financial reports. Reports will agregate data about sold products: average invoice price, sell price, sold quantity and nett income. Based on financial day reports user can crate week, month and year reports. Createing financial day report is very important step, because with this action user will update data for product information managment. 

[More info](Sections/Section%202/Report%20Generator.md)

[Return to top](#Barksville.shop)
### Product information managment

User can quicly check how many items of particular products are in magazine, how much was sold so far and how much nett income they genereted. In addition, to each product can be assigned minimum quantity. With that value user can automatically create list of products to order.

[More info](Sections/Section%202/PIM.md)

[Return to top](#Barksville.shop)
## Deployment to Amazon Web Services
I chose to use Amazon Web Services for deployment of this application. The main reason is Amazon free tier that include 1 year of free usage of vast majority of their services. The second one is that AWS is on of the leaders on the market. And the third one is the speed of the aplication after deployment compared to Heroku free services. 

### Relational Database Service
Creating database in AWS RDS is relativley easy task. The main challange is to fill in forms without paid features and to configurate security group. For my convinance i change inbound rules to connect through MySQL Workbench to database instance in RDS.
![DB Diagram](Sections/Section%203/RDS.png)

[Return to top](#Barksville.shop)
### Deploy application to Elastic Beanstalk
Elastic Beanstalk is an easy-to-use service for deploying and scaling web applications. And for me it realy is what it said it will be. To create web server environment runing java application i could use plain .jar file, .war file with tomcat or even make a docer image of it. For my porpuse the .war file was the best option. 
![DB Diagram](Sections/Section%203/BarksvilleApp.png)
For proper connection to RDS instance of database, i created environmental variables in application.properties file and in Elastic Beanstalk environment configurations.

```properties
spring.datasource.url=jdbc:mysql://${RDS_HOSTNAME:localhost}:${RDS_PORT:3306}/${RDS_DB_NAME:todos}?useUnicode=yes&characterEncoding=UTF-8
spring.datasource.username=${RDS_USERNAME:user}
spring.datasource.password=${RDS_PASSWORD:password}
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
```
![DB Diagram](Sections/Section%203/Variables.png)

[Return to top](#Barksville.shop)
### Configurating Route 54
In amazon web service there is no option to buy a Polish domain. In this scenario connecting app is a more complicated task compared to connecting it with supported domains in AWS. I bought domain at Polish distibutor and configurate it to cooperate with AWS. Last step was to conect website adress to my application instance by Route 53.

![DB Diagram](Sections/Section%203/Route%2053.png)

[Return to top](#Barksville.shop)
## Live version

Unfortunately i cannot give access to log in. The registration option is disabled.

[barkshop.pl](http://www.barkshop.pl/)

[Return to top](#Barksville.shop)

## Future plans

I expanded my knowledge greatly through this project. I am aware of many problems in my code that will be hard to repair and maintain. Thats why I decided to start from the scratch with TDD approach in:

 [BarkShop-REST-API](https://github.com/mgrtomaszzurawski/BarkShop-REST-API)
