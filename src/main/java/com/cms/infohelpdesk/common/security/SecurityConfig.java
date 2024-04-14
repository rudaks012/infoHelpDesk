package com.cms.infohelpdesk.common.security;//package com.cms.commons.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests(authorize -> authorize
//                .antMatchers("/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_INFOSEC", "ROLE_WHALE")
//                .antMatchers("/library/{libraryId}/**").access("hasAuthority('ROLE_LIB_'+#libraryId)")
//                .anyRequest().authenticated()
//            )
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.build();
//    }
//}
