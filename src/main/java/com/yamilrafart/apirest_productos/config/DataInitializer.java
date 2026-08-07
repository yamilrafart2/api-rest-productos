package com.yamilrafart.apirest_productos.config;

import com.yamilrafart.apirest_productos.entity.Rol;
import com.yamilrafart.apirest_productos.entity.Usuario;
import com.yamilrafart.apirest_productos.repository.RolRepository;
import com.yamilrafart.apirest_productos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear roles si no existen
            if (rolRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
                Rol rolAdmin = new Rol();
                rolAdmin.setNombre("ROLE_ADMIN");
                rolRepository.save(rolAdmin);
            }
            if (rolRepository.findByNombre("ROLE_USER").isEmpty()) {
                Rol rolUser = new Rol();
                rolUser.setNombre("ROLE_USER");
                rolRepository.save(rolUser);
            }

            // Crear usuario administrador
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN").get();
                admin.getRoles().add(rolAdmin);
                usuarioRepository.save(admin);
            }

            // Crear usuario estándar (solo lectura)
            if (usuarioRepository.findByUsername("user").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                Rol rolUser = rolRepository.findByNombre("ROLE_USER").get();
                user.getRoles().add(rolUser);
                usuarioRepository.save(user);
            }
        };
    }
}