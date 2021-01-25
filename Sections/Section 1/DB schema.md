# Database Schema

This page includes detail information about used MySql database in project.

[Main Page](../../README.md)

## Baisic information
Diagram below represent created tables and their relations between each other. For the purpose of the application there can be distincted four section of the database:
- Security layer with user accounts and their access level. 
- Informations about products like generated nett income.   
- Aggregating informations from shop reports and invoices to track items status.
- Storing periodic reports for quick access to important statistic.

![DB Diagram](DB%20diagram.png)

[Return to top](#Database-Schema)

## User Accounts
When i started building this application i tried to think ahead, but i did not know the YAGNI rule yet. I didn't implement java email sender for example, so there is no point for storing email information, but i created this field anyway. The main reason for this part of my database is to create account and asign user role to it. 

![DB Diagram](DB%20user.png)

[Return to top](#Database-Schema)

## Individual Items Lifecycle
The main think that shops do is buy items and sell them for more. Sometimes items pass expiration date or simply damage, then they must be removed from inventory. I think this short description show purposee of tables below. Items represents particular products in a given entry of invoice, sale or removal.

![DB Diagram](DB%20item%20life.png)

[Return to top](#Database-Schema)

## Product statistics
Product table stores statistical data that could be calculated based on items. Justification for creating product table is growing computing time for data like quantity, sales and income with the number of entries in item table. Computing that for every product each time there is request will implay huge loads on server. To remove that problem system updates product table when the new day report is created. 

![DB Diagram](DB%20product.png)

[Return to top](#Database-Schema)

## Periodic Reports
The core part of periodic reports is day report table. It contains most important data about transactions and week, month and year tables only aggregate days reports.

[Return to top](#Database-Schema)

![DB Diagram](DB%20reports.png)