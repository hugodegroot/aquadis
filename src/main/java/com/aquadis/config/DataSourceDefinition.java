package com.aquadis.config;

import javax.ejb.Stateless;

@javax.annotation.sql.DataSourceDefinition(
        name = "java:global/jdbc/aquadisDB",
        className = "com.mysql.cj.jdbc.MysqlXADataSource",
        url = "jdbc:mysql://oege.ie.hva.nl:3306/zgoedhal0031?createDatabaseIfNotExist=true&serverTimezone=CET",
        user = "goedhal0031",
        password = "Cc1$m8FubtIzX5")
@Stateless

public class DataSourceDefinition {
}