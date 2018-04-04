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

    //@Resource(name = "userDetailsService()")
    //private UserDetailsService userDetailsService;

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        /*inMemoryUserDetailsManager.createUser(User.builder().username("avinash").password(new BCryptPasswordEncoder().encode("password")).roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.builder().username("varsha").password(new BCryptPasswordEncoder().encode("password")).roles("ADMIN").build());*/
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("avinash").password("password").roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username("varsha").password("password").roles("ADMIN").build());
        //userDetailsService = inMemoryUserDetailsManager;
        return inMemoryUserDetailsManager;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/posts/post/list").permitAll()
                    .antMatchers("/posts/post/add").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/index.html");


    }
}
