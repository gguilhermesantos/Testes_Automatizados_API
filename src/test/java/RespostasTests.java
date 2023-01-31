import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RespostasTests {

    public static void testEnviaRespostas( String tipoTest) {
        String token = AuthenticationTests.autenticandoUsuario("autenticando");
        //int perguntaId = Integer.parseInt(PerguntasTests.testBuscaPergunta());
        //String perguntaDescricao = PerguntasTests.testBuscaPergunta();

        String[] fields = {};
        if (Objects.equals(tipoTest, "semAutenticacao")){
            fields = new String[]{String.valueOf(2),"false","   ", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "PerguntaNaoExiste")) {
            fields = new String[]{String.valueOf(1000),"false",token, String.valueOf(403)};
        } else if (Objects.equals(tipoTest, "RespostaDiff")) {
            fields = new String[]{String.valueOf(2),"teste",token, String.valueOf(403)};
        } else if (Objects.equals(tipoTest, "RespondidaCorretamente")) {
            fields = new String[]{String.valueOf(2),"false",token, String.valueOf(201)};
        } else if (Objects.equals(tipoTest, "PerguntaJaRespondida")) {
            fields = new String[]{String.valueOf(2),"false",token, String.valueOf(409)};
        } else{
            System.out.println("Erro ao gerar dado para Resposta");
        }

        HashMap<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("perguntaId", fields[0]);//perguntaId);
        jsonBody.put("respostaDada", fields[1]);
        Response response =
        given()
            .baseUri("http://165.227.93.41/cgitar")
            .basePath("/respostas")
            .contentType(ContentType.JSON)
            .header("token",fields[2])
                .body(jsonBody)
        .when()
                .post()
        .then()
                .statusCode(Integer.parseInt(fields[3]))
                //.log().all()
                //.body("message", equalTo(""))
                .extract().response();
        System.out.println("Finalizou teste: testEnviaRespostas --> " + tipoTest);
    }
    public static void testPegaRespostas(String tipoTest) {
        String token = AuthenticationTests.autenticandoUsuario("autenticando");
        String[] fields = {};
        if (Objects.equals(tipoTest, "PegaResposta")){
            fields = new String[]{token,String.valueOf(200)};
        } else if (Objects.equals(tipoTest, "semAutenticacao")) {
            fields = new String[]{"    ", String.valueOf(401)};
        } else{
            System.out.println("Erro ao gerar dado para Resposta");
        }
       // Response response =
        given()
                .baseUri("http://165.227.93.41/cgitar")
                .basePath("/respostas")
                .header("token",fields[0])
        .when()
                .get()
        .then()
                .statusCode(Integer.parseInt(fields[1]));
                //.log().all()
                //.extract().response();
        System.out.println("Finalizou teste: testPegaRespostas --> " + tipoTest);
    }
    public static void testEditarRespostas(String tipoTest) {
        String token = AuthenticationTests.autenticandoUsuario("autenticando");
        String[] fields = {};
        if (Objects.equals(tipoTest, "semAutenticacao")){
            fields = new String[]{"   ",String.valueOf(6813),String.valueOf(401),"false"};
        } else if (Objects.equals(tipoTest, "PerguntaNaoExiste")) {
            fields = new String[]{token,String.valueOf(9999),String.valueOf(403),"false"};
        } else if (Objects.equals(tipoTest, "RespostaDiff")){
            fields = new String[]{token,String.valueOf(6813),String.valueOf(403),"teste"};
        } else if (Objects.equals(tipoTest, "alteraResposta")) {
            fields = new String[]{token,String.valueOf(6813),String.valueOf(200),"false"};
        } else{
            System.out.println("Erro ao gerar dado para Resposta");
        }

        given()
                .baseUri("http://165.227.93.41/cgitar")
                .basePath("/respostas/"+fields[1])
                .contentType(ContentType.JSON)
                .header("token",fields[0])
        .when()
                .put()
        .then()
                .statusCode(Integer.parseInt(fields[2]));
        System.out.println("Finalizou teste: testEditarRespostas --> " + tipoTest);
    }

    @Test
    public void testRodarResposta(){

        RespostasTests.testEnviaRespostas("semAutenticacao");
        RespostasTests.testEnviaRespostas("PerguntaNaoExiste");
        //RespostasTests.testEnviaRespostas("RespostaDiff"); // diferente de true ou false
        //RespostasTests.testEnviaRespostas("RespondidaCorretamente");
        //RespostasTests.testEnviaRespostas("PerguntaJaRespondida");
        RespostasTests.testPegaRespostas("semAutenticacao"); //se nao for token vazio ocorre 500
        RespostasTests.testPegaRespostas("PegaResposta");
        RespostasTests.testEditarRespostas("semAutenticacao");
        RespostasTests.testEditarRespostas("PerguntaNaoExiste");
        RespostasTests.testEditarRespostas("RespostaDiff"); // diferente de true ou false
        //RespostasTests.testEditarRespostas("alteraResposta");
    }
}
