package fr.polytech.mtp.coviwad_documents.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public void insertFile() throws SQLException, IOException {
        File file = new File("myimage.gif");
        FileInputStream fis = new FileInputStream(file);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO documents VALUES (?, ?)");
        ps.setString(1, file.getName());
        ps.setBinaryStream(2, fis, (int)file.length());
        ps.executeUpdate();
        ps.close();
        fis.close();
    }

    @Override
    public ArrayList<File> getImagesByUserId() throws SQLException, IOException {
        PreparedStatement ps = conn.prepareStatement("SELECT file FROM documents WHERE user_id = ?");
        ps.setString(1, "1"); //TODO : find logged user id
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            byte[] imgBytes = rs.getBytes(1);
            // use the data in some way here
            String path = "new_img.png";
            Files.write(new File(path).toPath(), imgBytes);
        }
        rs.close();
        ps.close();

        return null;
    }
}
