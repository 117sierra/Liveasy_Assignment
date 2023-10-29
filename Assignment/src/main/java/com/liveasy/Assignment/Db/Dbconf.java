package com.liveasy.Assignment.Db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class Dbconf {

//    @Bean
//    public Connection getconnection() {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liveasy", "root", "gss@1009");
//            return  connection;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    @Bean
    public DataSource getdatasrc(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/liveasy");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("gss@1009");
        return dataSourceBuilder.build();

    }
}
