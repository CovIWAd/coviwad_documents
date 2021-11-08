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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long document_id;
    private String document_type;
    private String file;
    private Date test_date;
    private boolean is_positive;
    private String user_id;

    public Document(String document_type, String file, Date test_date, boolean is_positive, String user_id) {
        this.document_type = document_type;
        this.file = file;
        this.test_date = test_date;
        this.is_positive = is_positive;
        this.user_id = user_id;
    }

    public Document() {
    }

    public long getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public boolean isIs_positive() {
        return is_positive;
    }

    public void setIs_positive(boolean is_positive) {
        this.is_positive = is_positive;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
