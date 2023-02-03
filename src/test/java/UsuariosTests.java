import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class UsuariosTests {

    public static void testCriarUser(String tipoTest){
        String[] fields = {};
if (Objects.equals(tipoTest, "SemDado")) {
    fields = new String[]{"teste Gui", "", "majour.jet1@falltrack.net", "1187598745", String.valueOf(422)};
} else if (Objects.equals(tipoTest, "dadoErrado")) {
    fields = new String[]{"teste Gui", "majour.jet1@falltrack.net", null, "@Teste",String.valueOf(400)};
} else if (Objects.equals(tipoTest, "dadoCorreto")){
    fields = new String[]{"teste Gui", "majour.jet1@falltrack.net","majour.jet1@falltrack.net","1187598745",String.valueOf(201)};
} else if (Objects.equals(tipoTest, "dadoJaCriado")){
    fields = new String[]{"teste Gui", "majour.jet1@falltrack.net","majour.jet1@falltrack.net","1187598745",String.valueOf(409)};
} else {
    System.out.println("Erro ao gerar dado para criar teste de usuario");
}
            HashMap<String, Object> jsonBody = new HashMap<String, Object>();
            jsonBody.put("usuarioNomeCompleto", fields[0]);
            jsonBody.put("usuarioEmail", fields[1]);
            jsonBody.put("usuarioSenha", fields[2]);
        if (tipoTest != "dadoErrado") {
            jsonBody.put("usuarioTelefone", fields[3]);
        }

        given()
                .header("Content-Type","application/json")
                //.params()
                .body(jsonBody)
        .when()
                .post("http://165.227.93.41/cgitar/usuarios")
        .then()
                .statusCode(Integer.parseInt(fields[4]));
                //.log().all();
                //.body("error", equalTo("O usuário majour.jet1@falltrack.net já existe."));
                System.out.println("Finalizou teste: " + tipoTest);
    }
    @Test
    public void testRodarUsuarios(){
        UsuariosTests.testCriarUser("SemDado");
        UsuariosTests.testCriarUser("dadoErrado");
        //UsuariosTests.testCriarUser("dadoCorreto");
        UsuariosTests.testCriarUser("dadoJaCriado");
    }
}



//https://www.youtube.com/watch?v=EUbb5TyJbE8

//link 1: https://github.com/rest-assured/rest-assured/wiki/Usage
/*
como ter arquivo externo -- sim
vale a pena ter testes que nao rodam com frequencia? sim, tenta deletar se for stg prod nao
pq as vezes da erro L39 auth -- a validar o Response response =
como ter mais tags--rodar coisas especificas
tem como rodar no terminal?
nao pode ter varias classes
 */
