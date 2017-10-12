package my.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("my.springboot.app")
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = SpringApplication.run(Application.class,args);
	}

}
