package com.crudAccenture.accenture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("us3r")).authorities("USUARIO_ROLE")
                .and()
                .withUser("client").password(passwordEncoder().encode("cl1ent")).authorities("CLIENTE_ROLE")
                .and()
                .withUser("caixa").password(passwordEncoder().encode("c@ixa")).authorities("LIVRO_CAIXA_ROLE")
                .and()
                .withUser("contabil").password(passwordEncoder().encode("c@ntab1l")).authorities("CONTABIL_ROLE");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/usuarios/**").hasAuthority("USUARIO_ROLE")
                .antMatchers("/api/clientes/**").hasAuthority("CLIENTE_ROLE")
                .antMatchers("/api/livros-caixa/**").hasAuthority("LIVRO_CAIXA_ROLE")
                .antMatchers("/api/contabil/**").hasAuthority("CONTABIL_ROLE")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                .antMatchers("/**").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
