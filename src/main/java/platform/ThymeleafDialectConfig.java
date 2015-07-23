package platform;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;

/**
 * Created by pamajcher on 2015-07-23.
 */
public class ThymeleafDialectConfig {

        @Bean
        public SpringSecurityDialect springSecurityDialect(){
            return new SpringSecurityDialect();
        }
}
