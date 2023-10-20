package server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import server.DTO.NotesDTO;
import server.DTO.TagDTO;
import server.Mapper.NotesDTOMapper;
import server.exceptions.UrlRequestException;
import server.model.Accounts;
import server.model.Category;
import server.model.Notes;
import server.model.Tags;
import server.repository.AccountsRepository;
import server.repository.CategoryRepository;
import server.repository.NotesRepository;
import server.repository.TagsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hibernate.Hibernate.map;


@Service
@RequiredArgsConstructor
public class NotesService {
    private final NotesRepository notesRepository;
    private final NotesDTOMapper notesDTOMapper;
    private final CategoryRepository categoryRepository;
    private final AccountsRepository accountsRepository;
    private final TagsRepository tagsRepository;

    public List<NotesDTO> getNotes(String name) {
        List<NotesDTO> collect = notesRepository.findNotesByAccounts_NameUser(name)
                .stream()
                .map(notesDTOMapper)
                .collect(Collectors.toList());
        return collect;
    }

    public NotesDTO getNote(int id) {
        return notesRepository.findById(id)
                .map(notesDTOMapper)
                .orElseThrow();
    }

    public NotesDTO postNote(NotesDTO noteDTO) {
        Accounts accounts = accountsRepository.findByNameUser(noteDTO.accountName());
        Notes note = new Notes();
        if(notesRepository.existsByUrlAddress(noteDTO.url_address()))
        {
            throw new UrlRequestException("URL already exists", HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepository.findByName(noteDTO.category().getName()).orElseThrow();
        LocalDateTime time = LocalDateTime.now();
        note.setNoteId(noteDTO.id());
        note.setTitle(noteDTO.title());
        note.setContent(noteDTO.content());
        note.setCategory(category);
        note.setUrlAddress(noteDTO.url_address());
        note.setFavorite(note.isFavorite());
        note.setAccounts(accounts);
        note.setCreationDate(time);
        note.setModificationDate(time);


        notesRepository.save(note);
        Notes tagHelper = notesRepository.findByCreationDate(time);
        tagsRepository.deleteByNoteId(tagHelper.getNoteId());

        for (TagDTO temp : noteDTO.tags()) {
            Tags toSave = new Tags();
            toSave.setAccountId(tagHelper.getAccounts().getAccountId());
            toSave.setNoteId(tagHelper.getNoteId());
            toSave.setDescription(temp.name());
            tagsRepository.save(toSave);
        }


        return noteDTO;
    }

    public NotesDTO putNote(NotesDTO note) {
        Category category = categoryRepository.findByName(note.category().getName()).orElseThrow();
        Notes noteToUpdate = notesRepository.findById(note.id()).orElseThrow();
        if (!noteToUpdate.getUrlAddress().equals(note.url_address()))
        {
            if(notesRepository.existsByUrlAddress(note.url_address()))
            {
                throw new UrlRequestException("URL already exists", HttpStatus.BAD_REQUEST);
            }
        }
        tagsRepository.deleteByNoteId(noteToUpdate.getNoteId());

        noteToUpdate.setContent(note.content());
        noteToUpdate.setTitle(note.title());
        noteToUpdate.setModificationDate(LocalDateTime.now());
        noteToUpdate.setUrlAddress(note.url_address());
        noteToUpdate.setCategory(category);
        noteToUpdate.setFavorite(note.favorite());

        notesRepository.save(noteToUpdate);


        for (TagDTO temp : note.tags()) {
            Tags toSave = new Tags();

            toSave.setNoteId(noteToUpdate.getNoteId());
            toSave.setAccountId(noteToUpdate.getAccounts().getAccountId());
            toSave.setDescription(temp.name());
            tagsRepository.save(toSave);
        }
        return note;
    }

    @Transactional
    public void deleteNote(NotesDTO note) {
        tagsRepository.deleteByNoteId(note.id());
        notesRepository.deleteById(note.id());
    }
    public NotesDTO getNoteByUrl(String url) {
        Notes notes = notesRepository.findByUrl(url);
        int id= notes.getNoteId();
        return notesRepository.findById(id)
                .map(notesDTOMapper)
                .orElseThrow();
    }
    public void postNoteByUrl(String url, Accounts loggedIn){
        Notes originalNote = notesRepository.findByUrl(url);
        Notes note = new Notes();
        Accounts accounts = accountsRepository.findByNameUser(loggedIn.getNameUser());
        note.setCategory(originalNote.getCategory());
        note.setContent(originalNote.getContent());
        note.setTitle(originalNote.getTitle());
        note.setAccounts(accounts);
        note.setModificationDate(LocalDateTime.now());
        note.setCreationDate(originalNote.getCreationDate());
        String generatedUrl = UUID.randomUUID().toString();
        note.setUrlAddress(generatedUrl);
        notesRepository.save(note);

        Tags toSave = new Tags();
        toSave.setAccountId(accounts.getAccountId());
        toSave.setNoteId(note.getNoteId());
        toSave.setDescription("shared");
        tagsRepository.save(toSave);
    }
    public NotesDTO copyNotes(int id, Accounts loggedIn){
        Notes noteToCopy = notesRepository.findById(id).orElseThrow();
        Notes noteCopied = new Notes();
        Accounts accounts = accountsRepository.findByNameUser(loggedIn.getNameUser());
        noteCopied.setCategory(noteToCopy.getCategory());
        noteCopied.setContent(noteToCopy.getContent());
        noteCopied.setTitle(noteToCopy.getTitle());
        noteCopied.setAccounts(accounts);
        noteCopied.setModificationDate(LocalDateTime.now());
        noteCopied.setCreationDate(LocalDateTime.now());
        String generatedUrl = UUID.randomUUID().toString();
        noteCopied.setUrlAddress(generatedUrl);
        notesRepository.save(noteCopied);

        Tags toSave = new Tags();
        toSave.setAccountId(accounts.getAccountId());
        toSave.setNoteId(noteCopied.getNoteId());
        toSave.setDescription("copied");
        tagsRepository.save(toSave);
        int id_note= noteCopied.getNoteId();
        return notesRepository.findById(id)
                .map(notesDTOMapper)
                .orElseThrow();
    }
}
