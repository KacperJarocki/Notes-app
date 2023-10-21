package server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.Mapper.NotesDTOMapper;
import server.model.Notes;
import server.repository.AccountsRepository;
import server.repository.CategoryRepository;
import server.repository.NotesRepository;
import server.repository.TagsRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class NotesServiceTest {
	@InjectMocks
	private NotesService notesService;
	@Mock
	private NotesRepository notesRepository;
	@Mock
	private NotesDTOMapper notesDTOMapper;
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private AccountsRepository accountsRepository;
	@Mock
	private TagsRepository tagsRepository;
	@Mock
	private Notes notes;

	@Test
	void getNotesDoNotThrowExceptions() {
		assertDoesNotThrow(() -> notesService.getNotes("name"));
	}
}