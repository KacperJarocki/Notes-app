package server.Mapper;

import org.springframework.stereotype.Service;
import server.DTO.TagDTO;
import server.model.Tags;

import java.util.function.Function;

@Service
public class TagDTOMapper implements Function<Tags, TagDTO> {
    @Override
    public TagDTO apply(Tags tags){
        return new TagDTO(
                tags.getDescription()
        );
    }

}
