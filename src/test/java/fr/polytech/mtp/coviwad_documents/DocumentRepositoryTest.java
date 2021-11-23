package fr.polytech.mtp.coviwad_documents;

import com.github.database.rider.core.api.configuration.DBUnit;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.JUnit5RiderTestContext;
import fr.polytech.mtp.coviwad_documents.models.Document;
import fr.polytech.mtp.coviwad_documents.repositories.DocumentRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(JUnit4.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DocumentRepositoryTest {

    @Autowired
    MockMvc mockMvc;

    /*@Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() throws SQLException {
        return  (ConnectionHolder) dataSource.getConnection();
    }*/

    @Autowired
    private DocumentRepository repository;

    @Test
    @DataSet("documents.yml")
     void testFindAll () {
        List<Document> documents = Lists.newArrayList(repository.findAll());
        Assertions.assertEquals(2,documents.size(), " Expected 2 documents in the database");
    }
}
