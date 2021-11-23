package fr.polytech.mtp.coviwad_documents;

import com.c4_soft.springaddons.security.oauth2.test.annotations.Claims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.StringClaim;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.github.database.rider.junit5.DBUnitExtension;
import fr.polytech.mtp.coviwad_documents.controllers.DocumentController;
import fr.polytech.mtp.coviwad_documents.models.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.database.rider.core.api.dataset.DataSet;

import java.util.Date;

import static fr.polytech.mtp.coviwad_documents.TestUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(JUnit4.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ComponentScan(basePackageClasses = { KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class })
public class DocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockKeycloakAuth(value = "user")
    @DisplayName("GET /api/documents/:id - DOCUMENT BY ID")
    @DataSet("documents.yml")
    public void testGetDocumentById() throws Exception {
        Date date = new Date();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/documents/{id}",1))
                //Status
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Header
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/documents/1"))

                //Response
                .andExpect(jsonPath("$.documentId", is(1)))
                .andExpect(jsonPath("$.documentType", is("VACCINE")))
                .andExpect(jsonPath("$.file", is("a")))
                .andExpect(jsonPath("$.documentDate", is("")))
                .andExpect(jsonPath("$.is_positive", is(false)))
                .andExpect(jsonPath("$.userId", is("1")));
    }

    @Test
    @DisplayName("GET /api/documents/:id - DOCUMENT BY ID - NOT FOUND")
    @DataSet("documents.yml")
    void testGetDocumentByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/documents/{id}",99))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/documents - ADD DOCUMENT")
    @DataSet("documents.yml")
    public void testPostDocument() throws Exception {

        Date date = new Date();
        //Document mockDocument = new Document(1,"VACCINE", "a", date, false, "1");
        Document postDocument = new Document("VACCINE", "c", date, false, "1");

        //doReturn(mockDocument).when(service).save(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postDocument))
                )
                //Status
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Response
                .andExpect(jsonPath("$.documentId", is(3)))
                .andExpect(jsonPath("$.documentType", is("VACCINE")))
                .andExpect(jsonPath("$.file", is("c")))
                .andExpect(jsonPath("$.documentDate", is(date)))
                .andExpect(jsonPath("$.is_positive", is(false)))
                .andExpect(jsonPath("$.userId", is(1)));
    }
}