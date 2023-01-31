import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PerguntasTests {
    public static int testBuscaPergunta(String tipoTest){
        String token = AuthenticationTests.autenticandoUsuario("autenticando");
        String[] fields = {};
        if (Objects.equals(tipoTest, "semAutenticacao")){
            fields = new String[]{"   ", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "pegarPerguntaCorreta")) {
            fields = new String[]{token, String.valueOf(200)};
        } else if (Objects.equals(tipoTest, "envioPerguntaExcedente")) {
            fields = new String[]{token, String.valueOf(204)};
        } else{
            System.out.println("Erro ao gerar dado para pegar pergunta");
        }
        Response response =
        given()
                .baseUri("http://165.227.93.41/cgitar")
                .basePath("/perguntas")
                .contentType(ContentType.JSON)
                .header("token",fields[0])
        .when()
                .get()
        .then()
                .statusCode(Integer.parseInt(fields[1]))
                //.body("message", equalTo("Pergunta selecionada de maneira aleatória"));
        .extract().response();
        if (Objects.equals(tipoTest, "pegarPerguntaCorreta")) {
            response.path("message", String.valueOf(equalTo("Pergunta selecionada de maneira aleatória")));
            int perguntaId = response.path("data.perguntaId");
            String perguntaDescricao = response.path("data.perguntaDescricao");
            System.out.println(perguntaId);
            System.out.println(perguntaDescricao);
            return perguntaId;
        }
        System.out.println("Finalizou teste: testBuscaPergunta --> " + tipoTest);
        return 0;
    }
    @Test
    public void testRodarPergunta(){
        PerguntasTests.testBuscaPergunta("semAutenticacao");
        //PerguntasTests.testBuscaPergunta("pegarPerguntaCorreta");
        PerguntasTests.testBuscaPergunta("envioPerguntaExcedente");
    }
}

