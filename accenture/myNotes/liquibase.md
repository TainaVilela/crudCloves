mvn liquibase --changeLogFile=dbchangelog.xml generateChangeLog
mvn liquibase:diff
mvn liquibase:update
mvn liquibase:clearCheckSums