package server.DTO;


import server.model.Category;

import java.time.LocalDateTime;
import java.util.List;

public record NotesDTO(
        Integer id,
        String title,
        String content,
        String accountName,
        LocalDateTime modificationDate,
        String url_address,
        boolean favorite,
        Category category,
        List<TagDTO> tags

){
}
