package Logic.API_Requests;

import Logic.Exceptions.APIResponseException;
import Logic.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestsTest {

    @Test
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
    void login() {
        User user = new User();
        user.setUsername("Fabio");
        user.setPassword("olaola");

        boolean test = false;
        try {
            test = UserRequests.login(user.getUsername(), user.getPassword());

        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertTrue(test);
        //assertEquals(true, test);
    }

    @Test
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

    @Test
    void projects() {
    }

    @Test
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

        assertNotNull(test);
        //assertEquals(name, test);
    }

    @Test
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
    void search() {
        String name = "Hugo";
        User test = null;
        try {
            test = UserRequests.search(name);

        } catch (IOException e) {
            fail(e.getMessage());
        } catch (APIResponseException e) {
            e.printStackTrace();
        }

        User hugo = new User(2,"Hugo", "hugo@123", "071eee15edbb1d4493177690b3734054");
        assertNotNull(test);
        //assertEquals(hugo, test);

    }
}
