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
            if (rolRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
                Rol rolAdmin = new Rol();
                rolAdmin.setNombre("ROLE_ADMIN");
                rolRepository.save(rolAdmin);
            }

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña encriptada

                Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN").get();
                admin.getRoles().add(rolAdmin);

                usuarioRepository.save(admin);
            }
        };
    }
}