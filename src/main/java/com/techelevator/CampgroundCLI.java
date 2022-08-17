package com.techelevator;

import com.techelevator.controllers.CampgroundApplication;
import org.apache.commons.dbcp2.BasicDataSource;

public class CampgroundCLI {

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundApplication application = new CampgroundApplication(dataSource);

		application.run();
	}

}
