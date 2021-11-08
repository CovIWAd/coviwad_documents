package fr.polytech.mtp.coviwad_documents.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/*enum Type {
    VACCINE,
    PCR,
    ANTIGENIC
}*/

@Entity(name="documents")
@Access(AccessType.FIELD)
public class Document {

    @Id
    private String document_id;
    private String document_type;
    private Byte[] file;
    private Date test_date;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // pas de cycle
    private User user;

    public Document(String document_id, String document_type, Byte[] file, Date test_date) {
        this.document_id = document_id;
        this.document_type = document_type;
        this.file = file;
        this.test_date = test_date;
    }

    public Document() {
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
