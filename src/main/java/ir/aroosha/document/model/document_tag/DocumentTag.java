package ir.aroosha.document.model.document_tag;

import ir.aroosha.document.base.BaseEntity;
import ir.aroosha.document.model.document.Document;
import ir.aroosha.document.model.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "document_tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTag implements Serializable {

    @EmbeddedId
    private DocumentTagId id;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    @ManyToOne
    @MapsId("documentId")
    private Document document;
}
