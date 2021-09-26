package csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = CSVController.class)
public class TiendaAlemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaAlemApplication.class, args);
	}

}
