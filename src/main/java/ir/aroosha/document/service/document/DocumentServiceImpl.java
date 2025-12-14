package ir.aroosha.document.service.document;

import ir.aroosha.document.dto.DocumentSaveUpdateRequest;
import ir.aroosha.document.mapper.DocumentMapper;
import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.document_tag.DocumentTag;
import ir.aroosha.document.model.document_tag.DocumentTagId;
import ir.aroosha.document.model.enums.SearchMode;
import ir.aroosha.document.model.tag.Tag;
import ir.aroosha.document.repository.document.DocumentRepository;
import ir.aroosha.document.repository.document_tag.DocumentTagRepository;
import ir.aroosha.document.service.tag.TagService;
import ir.aroosha.document.util.DocumentSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final TagService tagService;
    private final DocumentMapper documentMapper;
    private final DocumentTagRepository documentTagRepository;

    @Override
    public Document saveWithDTO(DocumentSaveUpdateRequest documentSaveUpdateRequest) {
        Document document = documentMapper.mapToEntity(documentSaveUpdateRequest);
        document.setCreatedAt(LocalDateTime.now());
        Document savedDocument = documentRepository.save(document);
        for (String tagName : documentSaveUpdateRequest.getTags()) {
            Tag tag = tagService.getOrCreate(tagName);
            DocumentTag documentTag = new DocumentTag();
            documentTag.setTag(tag);
            documentTag.setDocument(document);
            documentTag.setId(new DocumentTagId(tag.getId(), savedDocument.getId()));
            documentTagRepository.save(documentTag);
        }
        return savedDocument;
    }

    @Override
    public Page<Document> search(String query, SearchMode searchMode, Pageable pageable) {
        Specification<Document> spec =
                DocumentSpecification.search(
                        searchMode,
                        query
                );

        return documentRepository.findAll(spec, pageable);
    }
}
