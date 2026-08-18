import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    @Step("Удаление пользователя через API")
    public static void deleteUser(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return;
        }

        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }

    @Step("Логин пользователя через API для получения accessToken")
    public static String loginUser(String email, String password) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password))
                .when()
                .post("/api/auth/login");

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        }
        return null;
    }

    @Step("Создание пользователя через API")
    public static String createUser(String name, String email, String password) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}", email, password, name))
                .when()
                .post("/api/auth/register");

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        }
        return null;
    }
}