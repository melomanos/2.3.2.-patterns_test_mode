package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRequestStatusActive() {
        RegistrationInfo regInfo = DataGenerator.statusActive();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Личный кабинет")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRequestStatusBlocked() {
        RegistrationInfo regInfo = DataGenerator.statusBlocked();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRequestEmptyLogin() {
        RegistrationInfo regInfo = DataGenerator.emptyLogin();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Поле обязательно для заполнения")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRequestEmptyPassword() {
        RegistrationInfo regInfo = DataGenerator.emptyPassword();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Поле обязательно для заполнения")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRequestInvalidLogin() {
        RegistrationInfo regInfo = DataGenerator.invalidLogin();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRequestInvalidPassword() {
        RegistrationInfo regInfo = DataGenerator.invalidPassword();
        $("[data-test-id='login'] input").setValue(regInfo.login);
        $("[data-test-id='password'] input").setValue(regInfo.password);
        $$("[type='button']").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 3000);
    }
}
