package dev.niko.core.sentinel.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.niko.core.sentinel.server.application.user.request.UserRequest;
import dev.niko.core.sentinel.server.infrastructure.user.service.UserService;
import dev.niko.core.sentinel.server.shared.mapper.user.UserMapper;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService service, UserMapper mapper){
		return args -> {
			List<String> admin = new ArrayList<>();
			admin.add("ROLE_ADMIN");
			
			service.create(
				mapper.toMap(new UserRequest("rootadmin", "rootpass", admin))
			);
		};
	}
}
