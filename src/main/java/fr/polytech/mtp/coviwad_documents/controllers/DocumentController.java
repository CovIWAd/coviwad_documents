package fr.polytech.mtp.coviwad_documents.controllers;

import fr.polytech.mtp.coviwad_documents.models.Document;
import fr.polytech.mtp.coviwad_documents.models.DocumentType;
import fr.polytech.mtp.coviwad_documents.repositories.DocumentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/doc")
    public Document getLocation(@RequestParam(value = "document_type", defaultValue = "VACCINE") String document_type,
                                @RequestParam(value = "file", defaultValue = "") String file,
                                @RequestParam(value = "test_date", defaultValue = "2021-11-01") String test_date,
                                @RequestParam(value = "is_positive", defaultValue = "false") boolean is_positive,
                                @RequestParam(value = "user_id", defaultValue = "1") String user_id
    ) throws ParseException {
        System.out.println(DocumentType.valueOf(document_type));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(test_date);
        return new Document(document_type, file, date, Boolean.parseBoolean(String.valueOf(is_positive)), user_id);
    }

    @PostMapping("/doc")
    public Document postDocument(@RequestBody Document document) {
        try{
            DocumentType dt = DocumentType.valueOf(document.getDocumentType());
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return document;
    }

    @GetMapping
    public List<Document> list() {
        return documentRepository.findAll();
    }

    @GetMapping("/user/{idUser}")
    public List<Document> getDocumentsByUserId(@PathVariable String idUser){
        return documentRepository.findByUserId(idUser);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Document get(@PathVariable Long id) {
        if(!documentRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Document with ID "+id+" not found");
        }
        return documentRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Document create(@RequestBody final Document document) {
        return documentRepository.saveAndFlush(document);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s ’ il faut  supprimer aussi les enregistrements enfants
        documentRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Document update ( @PathVariable Long id , @RequestBody Document document) {
        // TODO: Distinguer les deux erreurs
        if(id != null && document != null && documentRepository.findById(id).isPresent()) {
            Document existingDocument = documentRepository.getById(id);
            BeanUtils.copyProperties(document,existingDocument,"document_id");
            return documentRepository.saveAndFlush(existingDocument);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Missing arguments or Document with ID "+id+" not found");
        }
    }
}
