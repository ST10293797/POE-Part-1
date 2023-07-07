/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
import com.mycompany.userauthentication2.UserAuthentication2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAuthentication2Test {

    private UserAuthentication2 userAuth;

    @BeforeEach
    public void setup() {
        userAuth = new UserAuthentication2();
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        // Test data
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer"};

        // Assert
        assertArrayEquals(expectedDevelopers, userAuth.getDevelopers());
    }

    @Test
    public void testDisplayDeveloperAndDurationForTaskWithLongestDuration() {
        // Test data
        String expectedOutput = "Developer: Glenda Oberholzer\nDuration: 11 hours";

        // Assert
        assertEquals(expectedOutput, userAuth.displayDeveloperAndDurationForTaskWithLongestDuration());
    }

    @Test
    public void testSearchTasksByTaskName() {
        // Test data
        String taskName = "Create Login";
        String expectedOutput = "Developer: Mike Smith\nTask Name: Create Login";

        // Assert
        assertEquals(expectedOutput, userAuth.searchTasksByTaskName(taskName));
    }

    @Test
    public void testSearchTasksByDeveloper() {
        // Test data
        String developer = "Samantha Paulson";
        String expectedOutput = "Task Name: Create Reports\nTask Status: Done";

        // Assert
        assertEquals(expectedOutput, userAuth.searchTasksByDeveloper(developer));
    }

    @Test
    public void testDeleteTaskFromArray() {
        // Test data
        String taskName = "Create Reports";
        String expectedOutput = "Task 'Create Reports' successfully deleted.";

        // Assert
        assertEquals(expectedOutput, userAuth.deleteTaskFromArray(taskName));
    }

    @Test
    public void testDisplayReport() {
        // Test data
        String expectedOutput = "Task Report:\n\n" +
                "Task ID\t\tTask Name\t\tTask Description\t\tTask Duration\t\tDeveloper\t\tTask Status\n" +
                "T1\t\tCreate Login\t\t\t\t8\t\t\t\tMike Smith\t\t\tTo Do\n" +
                "T2\t\tCreate Reports\t\t\t\t6\t\t\t\tEdward Harrington\t\tDone\n";

        // Assert
        assertEquals(expectedOutput, userAuth.displayReport());
    }

    private void assertArrayEquals(String[] expectedDevelopers, Object developers) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
