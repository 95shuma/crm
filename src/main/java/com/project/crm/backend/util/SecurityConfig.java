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
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/default", true);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/register")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/profile")
                .authenticated();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Войти как администратор ЛПУ
        String fetchAdminsQuery = "select inn, password, enabled"
                + " from admins"
                + " where inn = ?";

        String fetchRolesQuery3 = "select a1.inn, a2.name"
                + " from admins a1, roles a2"
                + " where a1.role_id = a2.id"
                + " and a1.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchAdminsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery3)
                .dataSource(dataSource);
        // Войти как доктор
        String fetchDoctorsQuery = "select inn, password, enabled"
                + " from doctor"
                + " where inn = ?";

        String fetchRolesQuery2 = "select a2.inn, a3.name"
                + " from hospitals_doctor a1, doctor a2, roles a3"
                + " where a1.doctor_id = a2.id"
                + " and a1.role_id = a3.id"
                + " and a2.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchDoctorsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery2)
                .dataSource(dataSource);

        // Войти как пациент
        String fetchPatientsQuery = "select inn, password, enabled"
                + " from patients"
                + " where inn = ?";

        String fetchRolesQuery = "select a1.inn, a2.name"
                + " from patients a1, roles a2"
                + " where a1.role_id = a2.id"
                + " and a1.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchPatientsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery)
                .dataSource(dataSource);


    }
}
