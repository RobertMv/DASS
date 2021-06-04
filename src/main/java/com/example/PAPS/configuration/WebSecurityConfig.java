package com.example.PAPS.configuration;

import com.example.PAPS.entities.Role;
import com.example.PAPS.entities.User;
import com.example.PAPS.repositories.UserRepository;
import com.example.PAPS.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(MyUserDetailsService myUserDetailsService, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
    }


//    @PostConstruct
//    private void addAdmin(){
//        User user = new User();
//        if (!userRepository.existsById(1L)){
//            user.setEmployee(null);
//            user.setUsername("hr@test.com");
//            user.setRole(Role.HR);
//            user.setPassword(passwordEncoder().encode("hr"));
//            userRepository.save(user);
//        }
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        return daoAuthenticationProvider;
    }
}
