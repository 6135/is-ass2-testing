package mei.uc.is.SimpleProject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.*;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class SimpleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProjectApplication.class, args);
		
	}

	@Bean
	ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		ResourceDatabasePopulator resource =
			new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
		initializer.setDatabasePopulator(resource);
		return initializer;
	  }

}
