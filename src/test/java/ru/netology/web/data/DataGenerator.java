package ru.netology.web.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void setUpAll(RegistrationInfo regInfo) {
        // сам запрос
        given() // "дано"
            .spec(requestSpec) // указываем, какую спецификацию используем
            .body(regInfo) // передаём в теле объект, который будет преобразован в JSON
        .when() // "когда"
            .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
        .then() // "тогда ожидаем"
            .statusCode(200); // код 200 OK
    }

    public static RegistrationInfo statusActive() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo(login, password, status);
    }

    public static RegistrationInfo statusBlocked() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String status = "blocked";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo(login, password, status);
    }

    public static RegistrationInfo emptyLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = "";
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo(login, password, status);
    }

    public static RegistrationInfo emptyPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = "";
        String status = "active";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo(login, password, status);
    }

    public static RegistrationInfo invalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo("логин", password, status);
    }

    public static RegistrationInfo invalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new RegistrationInfo(login, password, status));
        return new RegistrationInfo(login, "пароль", status);
    }
}
