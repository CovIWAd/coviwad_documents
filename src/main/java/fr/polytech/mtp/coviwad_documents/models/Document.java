package fr.polytech.mtp.coviwad_documents.models;

import javax.persistence.*;
import java.util.Date;


@Entity(name="documents")
@Access(AccessType.FIELD)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="document_id")
    private long documentId;
    @Column(name="document_type")
    private String documentType;
    @Column(name="file")
    private String file;
    @Column(name="test_date")
    private Date testDate;
    @Column(name="is_positive")
    private boolean isPositive;
    @Column(name="user_id")
    private String userId;

    public Document(String documentType, String file, Date testDate, boolean is_positive, String userId) {
        this.documentType = documentType;
        this.file = file;
        this.testDate = testDate;
        this.isPositive = is_positive;
        this.userId = userId;
    }

    public Document() {
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int document_id) {
        this.documentId = document_id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String document_type) {
        this.documentType = document_type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date test_date) {
        this.testDate = test_date;
    }

    public boolean isIsPositive() {
        return isPositive;
    }

    public void setIsPositive(boolean is_positive) {
        this.isPositive = is_positive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }
}
