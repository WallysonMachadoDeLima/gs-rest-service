package br.com.softlutions.contrutora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.softlutions.contrutora", "br.com.softlutions.contrutora.modules", "br.com.softlutions.shared"})
@EntityScan(basePackages = {"br.com.softlutions.contrutora.modules", "br.com.softlutions.shared"})
@EnableJpaRepositories(basePackages = {"br.com.softlutions.contrutora.modules"})
public class RestServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestServiceApplication.class, args);
  }

}
