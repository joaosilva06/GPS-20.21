package Logic.API_Requests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestsTest {

    @Test
    void registar() {
        User user = new User();
        user.setUsername("Fabio");
        user.setEmail("fabio@ola");
        user.setPassword("olaola");

        User test = UserRequests.registar(user.getUsername(), user.getPassword(), user.getEmail());

        User user_result = new User(4, "Fabio", "fabio@ola", "071eee15edbb1d4493177690b3734054");

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
