package fr.loicp.simissman.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("fr.loicp.simissman")
@EnableJpaRepositories(basePackages = "fr.loicp.simissman")
public class JPAConfiguration {
}
