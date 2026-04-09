package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.util.TokenUtil;
import com.bridgelabz.fundoonotes.service.RedisService;
import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;
    private final RedisService redisService;

    public NoteServiceImpl(NoteRepository noteRepository,
                           UserRepository userRepository,
                           TokenUtil tokenUtil,
                           RedisService redisService) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
        this.redisService = redisService;
    }

    private User getUserFromToken(String token) {
        Long userId = redisService.getUserId(token);

        if (userId == null) {
            throw new RuntimeException("Invalid token");
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Note createNote(NoteRequestDto dto, String token) {

        Long userId = redisService.getUserId(token);

        if (userId == null) {
            throw new RuntimeException("Invalid token");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert DTO → Entity
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setPinned(dto.isPinned());
        note.setArchived(dto.isArchived());
        note.setTrashed(dto.isTrashed());
        note.setReminderTime(dto.getReminderTime());
        note.setUser(user);

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes(String token) {

        User user = getUserFromToken(token);
        return noteRepository.findByUserId(user.getId());
    }
}