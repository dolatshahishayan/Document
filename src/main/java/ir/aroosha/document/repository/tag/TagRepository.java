package ir.aroosha.document.repository.tag;

import ir.aroosha.document.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> , JpaSpecificationExecutor<Tag> {
    Optional<Tag> findByName(String name);
}
