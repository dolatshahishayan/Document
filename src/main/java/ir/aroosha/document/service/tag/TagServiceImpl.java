package ir.aroosha.document.service.tag;

import ir.aroosha.document.model.tag.Tag;
import ir.aroosha.document.repository.tag.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Override
    public Tag getOrCreate(String name) {
        String normalized = name.trim().toLowerCase();

        return tagRepository.findByName(normalized)
                .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(normalized);
                    return tagRepository.save(tag);
                });
    }
}
