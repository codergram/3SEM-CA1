/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
package rest;

import entities.members.GroupMember;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GroupMemberResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static GroupMember r1, r2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new GroupMember("Emil Elkjær Nielsen", "cph-en93@cphbusiness.dk", new String[]{"Keeping up with the kardasians", "Matador"});
        r2 = new GroupMember("Sigurd Arik Gaarde Nielsen", "cph-at89@cphbusiness.dk", new String[]{"Huset på Christianshavn", "SWAT"});
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void testCount() throws Exception {
        given()
                .contentType("application/json")
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", equalTo(2));
    }

    @Test
    void testGetById() throws Exception {
        int expectedId = r1.getId();

        //given().log().all().when().get("/groupmembers/all").then().log().body();

    given()
        .pathParam("id", expectedId)
        .contentType("application/json")
        .get("/groupmembers/{id}")
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("id", equalTo(expectedId));
    }

    @Test
    void testGetByName() throws Exception {
        String expectedName = r1.getName();

        given()
            .pathParam("name", "emil") //For at teste vores LIKE funktion
            .contentType("application/json")
            .get("/groupmembers/name/{name}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode())
            .body("name", equalToIgnoringCase(expectedName));
    }
}
