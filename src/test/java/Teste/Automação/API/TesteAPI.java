package Teste.Automação.API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;


public class TesteAPI {

    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void listarUsuarios() {  //Método criado para realizar o teste
        when().   //Quando ocorrer tal ação
                get("https://reqres.in/api/users?page=2"). //Link da API
        then().   //Resultado Esperado
                statusCode(HttpStatus.SC_OK).
                body("page", is(2)).
                body("per_page", is(6)).
                body("data", is(notNullValue()));
    }

    @Test
    public void listarUsuariosComErro() { //Método criado para realizar o teste
        when().     //Quando ocorrer tal ação
                get("https://reqres.in/api/users?page=2"). //Link da API
                then().
                statusCode(HttpStatus.SC_CREATED). //Status diferente para forçar um erro no teste
                body("page", is(2)).
                body("per_page", is(6)).
                body("data", is(notNullValue()));
    }

    @Test
    public void criarUsuariocomSucesso() {
        given().
                contentType(ContentType.JSON).
                body("{\"name\": \"vinicius\", \"job\": \"soft test\"}");
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(HttpStatus.SC_CREATED).
                body("name", is("vinicius"));
    }
}
