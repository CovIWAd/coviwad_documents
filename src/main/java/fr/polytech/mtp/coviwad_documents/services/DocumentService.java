package fr.polytech.mtp.coviwad_documents.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DocumentService {
    public abstract void insertFile() throws SQLException, IOException;
    public abstract ArrayList<File> getImagesByUserId() throws SQLException, IOException;
}
