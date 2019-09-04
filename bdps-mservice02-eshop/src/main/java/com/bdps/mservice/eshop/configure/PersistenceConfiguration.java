//package com.bdps.mservice.eshop.configure;
//
//import com.bdps.mservice.eshop.configure.audit.AuditorAwareImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//public class PersistenceConfiguration {
//    @Bean
//    AuditorAware<String> auditorProvider() {
//        return new AuditorAwareImpl();
//    }
//}
