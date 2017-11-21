# dataiku coding exercice

The application should allow the user to select a column from the database. It should then display,
for each value of the variable, the count of rows with this value and the average of the "age" value.
The values should be sorted by decreasing count.

It is OK to clip and only keep the first 100 values for a variable.

For simplicity, all columns (except the "age" column) are considered as string columns.
 
"Bonus points":

* When there are more than 100 values, it would be nice to know the number of values that were not displayed.
* Even better: know the number of rows that were clipped out.
* Try to plan for some extensibility: it should be easy to change the database file and the variables

The application uses java8 and jetty on the backend side, jquery and a bit of jquery-ui on the front end.

To build the application do mvn clean install (maven 3.3.9 is in use)
To run the application do mvn exec:java

The web app is served on port 8082.
Edit src/main/java/resources/config.yaml to modify the default settings.