package ir.aroosha.document.service.document;

import ir.aroosha.document.dto.DocumentSaveUpdateRequest;
import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.enums.SearchMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {
    Document saveWithDTO(DocumentSaveUpdateRequest documentSaveUpdateRequest);
    Page<Document> search(String query, SearchMode searchMode, Pageable pageable);
}