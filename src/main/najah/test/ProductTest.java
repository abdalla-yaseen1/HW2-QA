package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {

    Product p;

    @BeforeEach
    void setUp() {
        p = new Product("drill", 100);
    }

    @Test
    @DisplayName("Valid Discount")
    void testDiscount() {
        p.applyDiscount(10);
        assertEquals(90, p.getFinalPrice());
        assertEquals(10, p.getDiscount());
    }

    @Test
    @DisplayName("Invalid Discount")
    void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60));
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-20));

        
    }

    @Test
    @DisplayName("Negative Price")
    void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("phone", -5));
        assertThrows(IllegalArgumentException.class, () -> new Product("laptop", -30));

    }


    @DisplayName("Parameterized Discount Test")
    @ParameterizedTest
    @ValueSource(doubles = {5,10,20})
    void testParameterizedDiscount(double d) {
        p.applyDiscount(d);
        double expected = 100 - (100 * d / 100);
        
        assertEquals(expected, p.getFinalPrice());
        assertEquals(d, p.getDiscount());
    }
    
    
    @Test
    @Timeout(value=200,unit=TimeUnit.MILLISECONDS)
    @DisplayName("Timeout Test for Final Price")
    void testTimeout() {
        assertEquals(100, p.getFinalPrice());
    }
}




