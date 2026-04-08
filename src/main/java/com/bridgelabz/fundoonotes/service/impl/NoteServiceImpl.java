package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.util.TokenUtil;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public NoteServiceImpl(NoteRepository noteRepository,
                           UserRepository userRepository,
                           TokenUtil tokenUtil) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    private User getUserFromToken(String token) {
        Long userId = tokenUtil.decodeToken(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Note createNote(Note note, String token) {

        User user = getUserFromToken(token);
        note.setUser(user);

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes(String token) {

        User user = getUserFromToken(token);
        return noteRepository.findByUserId(user.getId());
    }
}