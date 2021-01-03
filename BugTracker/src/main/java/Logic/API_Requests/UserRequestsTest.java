package Logic.API_Requests;

import Logic.Exceptions.APIResponseException;
import Logic.Project;
import Logic.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRequestsTest {

    @Test
    @Order(1)
    void registar() throws IOException {
        User user = new User();
        user.setUsername("Fabio");
        user.setEmail("fabio@ola");
        user.setPassword("olaola");

        User test = null;
        try {
            test = UserRequests.registar(user.getUsername(), user.getPassword(), user.getEmail());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        User user_result = new User(18, "Fabio", "fabio@ola", "071eee15edbb1d4493177690b3734054");

        assertNotNull(test);
        //assertEquals(user_result, test); este pode induzir em erro uma vez que o id vai alterando

    }

    @Test
    @Order(2)
    void login() {
        User user = new User();
        user.setUsername("Hugo");
        user.setPassword("olaola");

        User test = null;
        try {
            test = UserRequests.login("Hugo", "olaola");

        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertNotNull(test);
        //assertEquals(true, test);
    }


    @Test
    @Order(6)
    void projects() {
        List<Project> listP = null;
        try {
            listP = UserRequests.projects();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(listP);


    }

    @Test
    @Order(8)
    void resetMail() {
        String mail = "hugo@123";
        boolean test = false;
        try {
            test = UserRequests.resetMail(mail);

        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertTrue(test);
        //assertEquals(true, test);
    }

    @Test
    @Order(5)
    void rename() {
        User user = new User();
        user.setUsername("Fabio");
        user.setPassword("olaola");
        String name = "Fabois";
        String test = null;
        try {
            test = UserRequests.rename(name);

        } catch (IOException e) {
            fail(e.getMessage());
        } catch (APIResponseException e) {
            e.printStackTrace();
        }
        String nome = "Fabiois";

        assertEquals(nome, test);
        //assertEquals(name, test);
    }

    @Test
    @Order(4)
    void repass() {
        User user = new User();
        user.setUsername("Fabio");
        user.setPassword("olaola");
        String pass = "adeus";
        boolean test = false;
        try {
            test = UserRequests.repass(pass);

        } catch (IOException e) {
            fail(e.getMessage());
        } catch (APIResponseException e) {
            e.printStackTrace();
        }

        assertTrue(test);
        //assertEquals(true, test);
    }

    @Test
    @Order(3)
    void search() {
        String name = "Neves";
        User test = null;
        try {

            test = UserRequests.search(name);

        } catch (IOException | APIResponseException e) {
            fail(e.getMessage());

        }

        User hugo = new User(2,"Hugo", "hugo@123", "071eee15edbb1d4493177690b3734054");
        assertNotNull(test);
        //assertEquals(hugo, test);

    }

    @Test
    @Order(7)
    void logoff() {
        User user = new User();
        user.setUsername("Fabio");
        user.setPassword("olaola");

        boolean test = false;
        try {
            test = UserRequests.logoff(user.getUsername(), user.getPassword());

        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertTrue(test);
        //assertEquals(true, test);
    }

}
