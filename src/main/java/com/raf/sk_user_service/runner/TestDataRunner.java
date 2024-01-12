package com.raf.sk_user_service.runner;

import com.raf.sk_user_service.domain.Client;
import com.raf.sk_user_service.domain.Role;
import com.raf.sk_user_service.repository.ClientRepository;
import com.raf.sk_user_service.repository.RoleRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private ClientRepository clientRepository;
    private RoleRepository roleRepository;

    public TestDataRunner(ClientRepository clientRepository, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("JWT Secret: " + base64Key);

        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager = new Role("ROLE_MANAGER", "Manager role");

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);

        Client admin = new Client();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        clientRepository.save(admin);
    }
}
