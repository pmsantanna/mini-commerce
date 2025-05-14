package com.minicommerce;

import com.minicommerce.model.Category;
import com.minicommerce.model.Product;
import com.minicommerce.model.User;
import com.minicommerce.repository.CategoryRepository;
import com.minicommerce.repository.ProductRepository;
import com.minicommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CategoryRepository categoryRepo, ProductRepository productRepo, UserRepository userRepo) {
        return args -> {
            if (categoryRepo.count() == 0) {
                Category eletr = new Category(); eletr.setName("Eletrônicos");
                Category livros = new Category(); livros.setName("Livros");
                categoryRepo.save(eletr);
                categoryRepo.save(livros);

                Product p1 = new Product();
                p1.setName("Smartphone Z1");
                p1.setDescription("Celular com ótimo custo-benefício");
                p1.setPrice(1999.90);
                p1.setImageUrl("img/smartphone.jpg");
                p1.setCategory(eletr);

                Product p2 = new Product();
                p2.setName("Livro Java");
                p2.setDescription("Aprenda Spring Boot do zero ao avançado");
                p2.setPrice(89.90);
                p2.setImageUrl("img/livro-java.jpg");
                p2.setCategory(livros);

                productRepo.save(p1);
                productRepo.save(p2);
            }

            if (userRepo.findByEmail("admin@admin.com").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setEmail("admin@admin.com");
                admin.setPassword(encoder.encode("123456"));
                admin.setAdmin(true);

                userRepo.save(admin);
                System.out.println("✅ Usuário admin criado: admin@admin.com / 123456");
            }
        };
    }
}
