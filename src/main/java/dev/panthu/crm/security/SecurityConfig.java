package dev.panthu.crm.security;

import org.hibernate.sql.results.graph.DatabaseSnapshotContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return userDetailsManager;
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() throws Exception {
        UserDetails anon = User.builder()
                .username("anon")
                .password("{noop}password")
                .roles("EMPLOYEE")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}password")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails berk = User.builder()
                .username("berk")
                .password("{noop}password")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(anon, john, berk);
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "api/customers").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "api/customers/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "api/customers").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/customers").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/customers/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

