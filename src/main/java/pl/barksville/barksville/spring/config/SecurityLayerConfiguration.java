package pl.barksville.barksville.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
/*
    Nie dodajemy adnotacji @EnableWebSecurity, bo to zostało już zrobione
    przez Spring Boot
 */
@EnableWebSecurity
public class SecurityLayerConfiguration extends WebSecurityConfigurerAdapter {
    /*
        Wstrzykiwane jest podstawowe źródło danych, skonfigurowane w pliku application.properties
     */
    private final DataSource dataSource;

    @Autowired
    public SecurityLayerConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, role_name FROM users_roles WHERE username = ?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").anonymous()
                .antMatchers("/logout").authenticated()
               // .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }
    /*@Bean
    DataSource dataSource() {
        DriverManagerDataSource dm = new DriverManagerDataSource();
        dm.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dm.setUrl("jdbc:mysql://localhost:3306/barksville?serverTimezone=UTC");
        dm.setUsername("root");
        dm.setPassword("root");
        return dm;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/","webjars/**").antMatchers("/media/","/media/**").antMatchers("/h2-console", "/h2-console/**");
    }
}
