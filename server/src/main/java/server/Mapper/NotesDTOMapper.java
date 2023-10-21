package server.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.DTO.NotesDTO;
import server.model.Notes;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotesDTOMapper  implements Function<Notes, NotesDTO> {
    private final TagDTOMapper tagDTOMapper;
    @Override
    public NotesDTO apply(Notes note){
        return new NotesDTO(
                note.getNoteId(),
                note.getTitle(),
                note.getContent(),
                note.getAccounts().getNameUser(),
                note.getModificationDate(),
                note.getUrlAddress(),
                note.isFavorite(),
                note.getCategory(),
                note.getTags()
                        .stream()
                        .map(tagDTOMapper)
                        .collect(Collectors.toList())

        );
    }
}
