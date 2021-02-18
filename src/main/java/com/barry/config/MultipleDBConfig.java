package com.barry.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultipleDBConfig {
	@Bean(name = "graph")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbctemplate1")
	public JdbcTemplate jdbcTemplate(@Qualifier("graph") DataSource dsMySQL) {
		return new JdbcTemplate(dsMySQL);
	}
	
	//user
	@Bean(name = "user")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource postgresDataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbctemplate2")
	public JdbcTemplate postgresJdbcTemplate(@Qualifier("user") 
                                              DataSource dsMySQL) {
		return new JdbcTemplate(dsMySQL);
	}
}