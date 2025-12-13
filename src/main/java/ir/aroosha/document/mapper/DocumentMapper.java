package ir.aroosha.document.mapper;

import ir.aroosha.document.dto.DocumentSaveUpdateRequest;
import ir.aroosha.document.model.document.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentMapper {
    Document mapToEntity(DocumentSaveUpdateRequest documentSaveUpdateRequest);
}
