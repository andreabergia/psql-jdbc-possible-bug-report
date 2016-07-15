A minimal test case for a (possible) but with PostgreSQL driver: when asked
to return the value of the automatically generated primary key column, the
driver generates an SQL statement quoting the column name. Since the case
of the column name in the statement doesn't match exactly the case used in the
CREATE TABLE statement, the server returns an error.

The repository includes:
- a file `createTable.sql`, with the SQL code to create the table;
- a tiny Java program, with a Gradle project, to reproduce the issue.

The test Java program assumes that it can connect to a database named
"sampledb", using "sampledb" as username and an empty password,
on localhost.
