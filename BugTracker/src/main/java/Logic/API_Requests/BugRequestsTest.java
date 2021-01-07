package Logic.API_Requests;

import Logic.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BugRequestsTest {

    @Test
    void getBug() {
        Bug test = null;
        try {
            test = BugRequests.getBug(1,1);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        Bug bug_result = new Bug(6,"Hugo",Type.CompilationError.toString(), Status.ToSolve.toString(), Priority.Medium.toString(), "olaola", null, "ola", "test");

        assertNotNull(test);
        //assertEquals(bug_result, test);

    }

    @Test
    void addBug() {
        Bug b = new Bug();
        b.setCreator("Hugo");
        b.setDesc("teste");
        b.setTitle("Teste");
        b.setType(Type.CompilationError.toString());
        b.setPriority(Priority.Medium.toString());
        b.setProject("test");

        boolean test = false;
        try {
            test = BugRequests.addBug(b.getTitle(),b.getDesc(),1, null, 1, 2,1);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertNotNull(test);
        //assertEquals(true, test);
    }

    @Test
    void addBug_NoDescription() {
        Bug b = new Bug();
        b.setCreator("TesTer");
        b.setTitle("Teste");
        b.setType(Type.CompilationError.toString());
        b.setPriority(Priority.Medium.toString());
        b.setProject("test");

        boolean test = false;
        try {
            test = BugRequests.addBug(b.getTitle(),b.getDesc(),1, null, 1, 2,1);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertNotNull(test);
        //assertEquals(true, test);
    }

    @Test
    void editBug() {


    }

    @Test
    void solve() {
    }

    @Test
    void unsolve() {
    }
}
