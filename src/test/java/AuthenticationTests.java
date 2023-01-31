import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTests {

    public static String autenticandoUsuario(String tipoTest){
        String[] fields = {};
        if (Objects.equals(tipoTest, "emailErrado")){
            fields = new String[]{"   ","majour.jet1@falltrack.net", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "senhaErrada")) {
            fields = new String[]{"majour.jet1@falltrack.net","   ", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "autenticando")) {
            fields = new String[]{"majour.jet1@falltrack.net","majour.jet1@falltrack.net", String.valueOf(200)};
        } else{
            System.out.println("Erro ao gerar dado para autenticar usuario");
        }
        HashMap<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("usuarioEmail", fields[0]);
        jsonBody.put("usuarioSenha", fields[1]);
        Response response =
            given()
                .baseUri("http://165.227.93.41/cgitar")
                .basePath("/autenticacao")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                //.params()
                //.body(requestParams.toJSONString())
                .body(jsonBody)
            .when()
                    .post()
            .then()
                    //.extract().path("data.token")
                    .statusCode(Integer.parseInt(fields[2]))
                    .extract().response();
                    String token = ("Teste sem token");
                    if (Objects.equals(tipoTest, "autenticando")){
                        response.path("message", String.valueOf(equalTo("Sucesso ao realizar o login")));
                        token = response.path("data.token");
                    }
                    System.out.println("Finalizou teste: " + tipoTest);
                    //System.out.println(token); //sout
                    return token;
    }
    @Test
    public void testRodarAutenticacao(){
        AuthenticationTests.autenticandoUsuario("emailErrado");
        AuthenticationTests.autenticandoUsuario("senhaErrada");
        AuthenticationTests.autenticandoUsuario("autenticando");
    }
}