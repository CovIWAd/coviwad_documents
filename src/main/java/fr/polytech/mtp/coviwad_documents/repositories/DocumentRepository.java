package fr.polytech.mtp.coviwad_documents.repositories;
import fr.polytech.mtp.coviwad_documents.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
