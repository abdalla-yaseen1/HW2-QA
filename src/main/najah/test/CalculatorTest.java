package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Tests")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Start Calculator Tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("End Calculator Tests");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
    }

    @Test
    @Order(1)
    @DisplayName("Test add valid")
    void testAdd() {
        assertEquals(19, calc.add(5,6,8));
        assertEquals(100, calc.add(5,50,5,40));
        assertEquals(140, calc.add(5,10,15,20,25,30,35));
        assertEquals(30, calc.add(5,10,15));

    
    }

    @Test
    @Order(2)
    @DisplayName("Test divide valid")
    void testDivide() {
    	
        assertEquals(3, calc.divide(6,2));
        assertEquals(2, calc.divide(60,30));
        assertEquals(6, calc.divide(18,3));
        assertEquals(3, calc.divide(15,5));

    }

    @Test
    @Order(3)
    @DisplayName("Test divide by zero")
    void testDivideInvalid() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5,0));
        // from ai 
    }

    @Test
    @Order(4)
    @DisplayName("Test Factorial Valid")
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
        assertEquals(5040, calc.factorial(7));
        assertEquals(40320, calc.factorial(8));

    }

    @Test
    @Order(5)
    @DisplayName("Test factorial invalid ")
    void testFactorialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
        
       // from ai
    }

    @DisplayName("Test Factorial valid parameterized")
    @ParameterizedTest
    @ValueSource(ints = {1,5,8,35})
    void testParameterized(int num) {	
        assertFalse(calc.factorial(num) < 0);
    }

    
    @Test
    @Timeout(value=200,unit=TimeUnit.MILLISECONDS)
    @DisplayName("Test adding time validation ")
    void testTimeout() {
    	assertEquals(15, calc.add(1,2,3,4,5));    }

    @Disabled("@Disabled adding test")
    @Test
    void failingTest() {
        assertEquals(10, calc.add(2,3)); 
    }
}
