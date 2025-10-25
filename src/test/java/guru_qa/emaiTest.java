package guru_qa;

import org.junit.jupiter.api.*;

import java.rmi.AccessException;
//@TestMethodOrder(MethodOrderer.class)
@DisplayName("Тесты email")
public class emaiTest {

    @Test
    @DisplayName("Email должен быть отправлен новому пользователю")
    //@Disabled("Номер бага")
    @Tag("Web")
    //@Order(2)
        void emailShouldBeSentForNewUser()
    {
        System.out.println("Hello");
        //throw new AssertionError("Падаем!");
    }

    @Test
    @DisplayName("Email должен быть Не отправлен новому пользователю")
    //@Disabled("Номер бага")
    @Tag("Web")
    //@Order(1)
    void emailShouldBeSentForNewUser2()
    {
        System.out.println("Hello");
        throw new AssertionError("Падаем!");
    }
}
