package com.ska.sf.net.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("avinash").password("password").roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("varsha").password("password").roles("ADMIN").build());

        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .authorizeRequests()
                    .antMatchers("/posts/post/list").permitAll()
                    .antMatchers("/posts/post/add").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                .and()
                    .logout();


    }
}
