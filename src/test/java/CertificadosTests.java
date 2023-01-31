import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CertificadosTests {
    public static void testGerarCertificado(String tipoTest) {
        String token = AuthenticationTests.autenticandoUsuario("autenticando");

        String[] fields = {};
        if (Objects.equals(tipoTest, "semAutenticacao")){
            fields = new String[]{"    ", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "SemTerCertificado")) {
            fields = new String[]{token, String.valueOf(403)};
        } else if (Objects.equals(tipoTest, "Certificado")) {
            fields = new String[]{token, String.valueOf(201)};
        } else{
            System.out.println("Erro ao gerar dado para Certificados");
        }

        given()
                .baseUri("http://165.227.93.41/cgitar")
                .basePath("/certificados")
                .contentType(ContentType.JSON)
                .header("token",fields[0])
                .body("{\n" +
                        "  \"apresentarPercentual\": true\n" +
                        "}")
        .when()
                .post()
        .then()
                .statusCode(Integer.parseInt(fields[1]));
        System.out.println("Finalizou teste: testGerarCertificado --> " + tipoTest);
    }
    public static void testPegarCertificado(String tipoTest) {
        String token = AuthenticationTests.autenticandoUsuario("autenticando");
        String token_auternativo = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c3VhcmlvaWQiOiI5MDIiLCJ1c3Vhcmlvbm9tZSI6InRlc3RlIEd1aSIsInVzdWFyaW9lbWFpbCI6Im1ham91ci5qZXRAZmFsbHRyYWNrLm5ldCIsInVzdWFyaW90ZWxlZm9uZSI6IjExODc1OTg3NDUifQ.jl-h2TCfnurXDwfnzW6v2L7w2jBB7qBvUKsJGdf5cQ4";
        String[] fields = {};
        if (Objects.equals(tipoTest, "semAutenticacao")){
            fields = new String[]{"    ", String.valueOf(401)};
        } else if (Objects.equals(tipoTest, "SemTerCertificado")) {
            fields = new String[]{token_auternativo, String.valueOf(204)};
        } else if (Objects.equals(tipoTest, "Certificado")) {
            fields = new String[]{token, String.valueOf(200)};
        } else{
            System.out.println("Erro ao gerar dado para Certificados");
        }
        Response response =
                given()
                        .baseUri("http://165.227.93.41/cgitar")
                        .basePath("/certificados")
                        .header("token", fields[0])
                        .when()
                        .get()
                        .then()
                        .statusCode(Integer.parseInt(fields[1]))
                        .extract().response();

        if (Objects.equals(tipoTest, "Certificado")) {
            int porcentagem = response.path("data.percentualAcerto");
            if (porcentagem > 69) {
                System.out.println("Porcentagem acima dos 70 para teste Certificado");
            }
        }
        System.out.println("Finalizou teste: testPegarCertificado --> " + tipoTest);
    }
    @Test
    public void testRodarCertificados(){

        CertificadosTests.testGerarCertificado("semAutenticacao");
        CertificadosTests.testGerarCertificado("SemTerCertificado");
        //CertificadosTests.testGerarCertificado("Certificado");

        CertificadosTests.testPegarCertificado("semAutenticacao");
        CertificadosTests.testPegarCertificado("SemTerCertificado");
        CertificadosTests.testPegarCertificado("Certificado");
    }
}
