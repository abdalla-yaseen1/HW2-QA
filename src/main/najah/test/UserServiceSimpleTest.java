package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.UserService;

@DisplayName("User Service Tests")
public class UserServiceSimpleTest {

    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    void testValidEmail() {
        assertTrue(service.isValidEmail("test@mail.com"));
    }

    @Test
    void testInvalidEmail() {
        assertFalse(service.isValidEmail("testmail"));
    }

    @Test
    void testAuthSuccess() {
        assertTrue(service.authenticate("admin", "1234"));
    }

    @Test
    void testAuthFail() {
        assertFalse(service.authenticate("user", "wrong"));
    }

   

}