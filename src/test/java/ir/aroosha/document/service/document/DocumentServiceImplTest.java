package ir.aroosha.document.service.document;

import ir.aroosha.document.dto.DocumentSaveUpdateRequest;
import ir.aroosha.document.mapper.DocumentMapper;
import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.tag.Tag;
import ir.aroosha.document.repository.document.DocumentRepository;
import ir.aroosha.document.repository.document_tag.DocumentTagRepository;
import ir.aroosha.document.service.tag.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private TagService tagService;

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private DocumentTagRepository documentTagRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test
    void saveWithDTO_ShouldSaveDocumentAndTags() {
        DocumentSaveUpdateRequest dto = new DocumentSaveUpdateRequest();
        dto.setTitle("Test Title");
        dto.setContent("Test Content");
        dto.setTags(List.of("ai", "machine"));

        Document document = new Document();
        document.setTitle(dto.getTitle());
        document.setContent(dto.getContent());

        when(documentMapper.mapToEntity(dto)).thenReturn(document);

        Document savedDocument = new Document();
        savedDocument.setId(1L);
        savedDocument.setTitle(document.getTitle());
        savedDocument.setContent(document.getContent());
        savedDocument.setCreatedAt(LocalDateTime.now());
        when(documentRepository.save(document)).thenReturn(savedDocument);

        Tag tag1 = new Tag(); tag1.setId(10L); tag1.setName("ai");
        Tag tag2 = new Tag(); tag2.setId(20L); tag2.setName("machine");
        when(tagService.getOrCreate("ai")).thenReturn(tag1);
        when(tagService.getOrCreate("machine")).thenReturn(tag2);

        Document result = documentService.saveWithDTO(dto);

        assertEquals(savedDocument, result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
        assertNotNull(result.getCreatedAt());

        verify(documentTagRepository).save(argThat(tag ->
                tag.getTag().getName().equals("ai")
        ));
        verify(documentTagRepository).save(argThat(tag ->
                tag.getTag().getName().equals("machine")
        ));
    }
}
