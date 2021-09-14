package security.corespringsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import security.corespringsecurity.repository.ResourcesRepository;
import security.corespringsecurity.service.SecurityResourceService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository);
        return securityResourceService;
    }
}
