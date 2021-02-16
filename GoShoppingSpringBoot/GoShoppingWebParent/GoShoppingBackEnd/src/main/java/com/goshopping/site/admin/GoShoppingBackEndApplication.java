package com.goshopping.site.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan({"com.goshopping.common.entity", "com.goshopping.site.admin.user"})
public class GoShoppingBackEndApplication {

	public static void main(String[] args) {

		SpringApplication.run(GoShoppingBackEndApplication.class, args);
	}
}
