package com.esig.todo.repositories;

import com.esig.todo.config.TestConfig;
import com.esig.todo.domain.user.User;
import com.esig.todo.domain.user.dtos.RegisterRequestDTO;

import jakarta.persistence.EntityManager;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@org.springframework.context.annotation.Import(TestConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should return user successfully")
    void testFindByEmailSuccess() {
        String email = "test@mail.com";
        RegisterRequestDTO register = new RegisterRequestDTO("Test", "test@mail.com", "123456");
        this.createUser(register);

        Optional<User> result = this.userRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user from DB when user not exists")
    void testFindByEmailFailed() {
        String email = "notexists@mail.com";

        Optional<User> result = this.userRepository.findByEmail(email);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(RegisterRequestDTO register) {
        User newUser = new User(null, register.username(), register.email(), register.password());
        this.entityManager.persist(newUser);
        return newUser;
    }
}
