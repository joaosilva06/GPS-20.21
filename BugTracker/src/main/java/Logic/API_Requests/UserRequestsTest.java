package Logic.API_Requests;

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

        User user_result = new User(1, "Fabio", "fabio@ola", "071eee15edbb1d4493177690b3734054");

        assertNotNull(test);
        assertEquals(user_result, test);

    }

    @Test
    void login() {
    }

    @Test
    void logoff() {
    }

    @Test
    void projects() {
    }

    @Test
    void resetPass() {
    }

    @Test
    void rename() {
    }

    @Test
    void repass() {
    }

    @Test
    void search() {
    }
}
