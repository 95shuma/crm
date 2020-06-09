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
                .logoutSuccessUrl("/login")
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

        String fetchRolesQuery3 = "select a.inn, r.name"
                + " from admins a, roles r"
                + " where a.role_id = r.id"
                + " and a.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchAdminsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery3)
                .dataSource(dataSource);
        // Войти как доктор
        String fetchDoctorsQuery = "select inn, password, enabled"
                + " from doctors"
                + " where inn = ?";

        String fetchRolesQuery2 = "select d.inn, r.name"
                + " from hospitals_doctors hd, doctors d, roles r"
                + " where hd.doctor_id = d.id"
                + " and hd.role_id = r.id"
                + " and d.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchDoctorsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery2)
                .dataSource(dataSource);

        // Войти как пациент
        String fetchPatientsQuery = "select inn, password, enabled"
                + " from patients"
                + " where inn = ?";

        String fetchRolesQuery = "select p.inn, r.name"
                + " from patients p, roles r"
                + " where p.role_id = r.id"
                + " and p.inn = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchPatientsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery)
                .dataSource(dataSource);


    }
}
