package ir.aroosha.document.service.tag;

import ir.aroosha.document.model.tag.Tag;

public interface TagService {
    Tag getOrCreate(String name);
}
