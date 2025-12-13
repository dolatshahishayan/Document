package ir.aroosha.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.aroosha.document.dto.DocumentSaveUpdateRequest;
import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.enums.SearchMode;
import ir.aroosha.document.service.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("documents")
@RequiredArgsConstructor
@Tag(name = "Documents controller", description = "Controller class for documents")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    @Operation(summary = "Save document", description = "Method for saving a document")
    public ResponseEntity<Long> save(@RequestBody DocumentSaveUpdateRequest document) {
        return ResponseEntity.ok(documentService.saveWithDTO(document).getId());
    }

    @GetMapping("/search")
    @Operation(summary = "Search document",description = "Advanced search for documents")
    public ResponseEntity<Page<Document>> searchDocuments(@RequestParam String query, @RequestParam SearchMode mode, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(documentService.search(query,mode, PageRequest.of(page,size)));

    }
}
