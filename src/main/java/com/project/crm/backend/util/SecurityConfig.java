package com.project.crm.backend.util;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/")
                .failureUrl("/?error=true")
                .defaultSuccessUrl("/default", true);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/default")
                .authenticated()
                .antMatchers("/admin/**")
                .hasRole(Constants.ADMIN)
                .antMatchers("/senior-doctor/**")
                .hasRole(Constants.SENIOR_DOCTOR)
                .antMatchers("/doctor/**")
                .hasRole(Constants.DOCTOR)
                .antMatchers("/junior-doctor/**")
                .hasRole(Constants.JUNIOR_DOCTOR)
                .antMatchers("/patient/**")
                .hasRole(Constants.PATIENT);

        http.authorizeRequests()
                .antMatchers("/")
                .anonymous()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String fetchAdminsQuery = "select inn, password, enabled"
                + " from users"
                + " where inn = ?";

        String fetchRolesQuery = "select u.inn, r.name" +
                " from users u, registrations_journal rj,  roles r" +
                " where u.id = rj.user_id and rj.role_id = r.id and u.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchAdminsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery)
                .dataSource(dataSource);
    }
}
