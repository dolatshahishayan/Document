package ir.aroosha.document.repository.document;

import ir.aroosha.document.model.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> , JpaSpecificationExecutor<Document> {

}
