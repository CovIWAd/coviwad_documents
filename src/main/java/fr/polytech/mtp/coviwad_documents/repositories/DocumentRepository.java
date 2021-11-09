package fr.polytech.mtp.coviwad_documents.repositories;
import fr.polytech.mtp.coviwad_documents.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByUserId(String user_id);
}
