package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("RecipeBook Tests")
public class RecipeBookTest {

    RecipeBook book;
    Recipe recipe;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting RecipeBook Tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("All RecipeBook Tests Finished");
    }

    @BeforeEach
    void setUp() {
        book = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Coffee");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test finished");
    }

    @Test
    @DisplayName("Add Recipe Successfully")
    void testAddRecipe() {
        assertTrue(book.addRecipe(recipe));
        assertEquals("Coffee", book.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Duplicate Recipe Should Fail")
    void testDuplicateRecipe() {
        book.addRecipe(recipe);
        assertFalse(book.addRecipe(recipe));
    }

    @Test
    @DisplayName("Delete Recipe Successfully")
    void testDeleteRecipe() {
        book.addRecipe(recipe);

        String deletedName = book.deleteRecipe(0);

        assertEquals("Coffee", deletedName);
        assertNotNull(book.getRecipes()[0]); // صار Recipe جديد
    }


   
    @Test
    @DisplayName("Invalid Index Should Throw Exception")
    void testInvalidIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            book.deleteRecipe(10);
        });
    }

    @Test
    @DisplayName("Edit Recipe Successfully")
    void testEditRecipe() {
        book.addRecipe(recipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Tea");

        String oldName = book.editRecipe(0, newRecipe);

        assertEquals("Coffee", oldName);
        assertEquals("", book.getRecipes()[0].getName()); // حسب الكود
    }

}
