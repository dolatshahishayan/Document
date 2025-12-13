package ir.aroosha.document.repository.document_tag;

import ir.aroosha.document.model.document_tag.DocumentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface DocumentTagRepository extends JpaRepository<DocumentTag, Long> , JpaSpecificationExecutor<DocumentTag> {
}
