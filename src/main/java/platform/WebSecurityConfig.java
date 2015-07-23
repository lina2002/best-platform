package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by pamajcher on 2015-07-21.
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        The following java security config:
        -allows all static css,images and scripts to everyone
        -defines two roles: USER and ADMIN
        -only allows role ADMIN to view /edit
         */

        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/", "/courses", "/course","/greeting").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/edit")
                .hasRole("ADMIN").anyRequest().authenticated() // 7
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN", "USER");
    }
}
