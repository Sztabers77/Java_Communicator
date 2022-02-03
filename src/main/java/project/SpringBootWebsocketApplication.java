package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

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

//		List<Map<String,Object>> list = jdbcTemplate.queryForList("use communicator;");
		List<Map<String,Object>> list2 = jdbcTemplate.queryForList("show tables;");

		if (list2.size() == 0){
			System.out.println("PUSTA BAZA");
		}

		for(Map<String,Object> bases : list2){
			System.out.println(bases);
		}

	}
}

