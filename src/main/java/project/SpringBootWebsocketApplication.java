package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import project.utils.XmlParser;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBootWebsocketApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootWebsocketApplication.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebsocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}

