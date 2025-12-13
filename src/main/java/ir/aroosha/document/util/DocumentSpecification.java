package ir.aroosha.document.util;

import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.document_tag.DocumentTag;
import ir.aroosha.document.model.enums.SearchMode;
import ir.aroosha.document.model.tag.Tag;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    public static Specification<Document> search(
            SearchMode mode,
            String text
    ) {
        return (root, query, cb) -> {

            if (text == null || text.isBlank()) {
                return cb.conjunction();
            }

            String like = "%" + text.toLowerCase() + "%";

            return switch (mode) {

                case TITLE -> cb.like(
                        cb.lower(root.get("title")),
                        like
                );

                case CONTENT -> cb.like(
                        cb.lower(root.get("content")),
                        like
                );

                case TAG -> tagPredicate(root, query, cb, like);

                case ALL -> {
                    Predicate title = cb.like(
                            cb.lower(root.get("title")),
                            like
                    );
                    Predicate content = cb.like(
                            cb.lower(root.get("content")),
                            like
                    );
                    Predicate tag = tagPredicate(root, query, cb, like);

                    yield cb.or(title, content, tag);
                }
            };
        };
    }
            private static Predicate tagPredicate(
            Root<Document> root,
            CriteriaQuery<?> query,
            CriteriaBuilder cb,
            String like
    ) {

        Subquery<Long> subQuery = query.subquery(Long.class);
        Root<DocumentTag> documentTag = subQuery.from(DocumentTag.class);
        Join<DocumentTag, Tag> tag = documentTag.join("tag");

        subQuery.select(documentTag.get("document").get("id"))
                .where(
                        cb.like(
                                cb.lower(tag.get("name")),
                                like
                        )
                );

        return root.get("id").in(subQuery);
    }
}
