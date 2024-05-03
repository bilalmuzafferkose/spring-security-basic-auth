package com.bilalkose.springsecuritybasicauth;

import com.bilalkose.springsecuritybasicauth.dto.CreateUserRequest;
import com.bilalkose.springsecuritybasicauth.model.Role;
import com.bilalkose.springsecuritybasicauth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.Set;

@SpringBootApplication()
public class SpringSecurityBasicAuthApplication implements CommandLineRunner {

	private final UserService userService;

    public SpringSecurityBasicAuthApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyUsers();
	}

	private void createDummyUsers() {
		CreateUserRequest user1 = CreateUserRequest.builder()
				.name("User")
				.username("user")
				.password("password")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(user1);

		CreateUserRequest user2 = CreateUserRequest.builder()
				.name("Admin")
				.username("admin")
				.password("password")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(user2);


		CreateUserRequest user3 = CreateUserRequest.builder()
				.name("Bilal Muzaffer")
				.username("bilal")
				.password("password")
				.authorities(Set.of(Role.ROLE_BILAL))
				.build();
		userService.createUser(user3);
	}
}
