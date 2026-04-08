package com.bridgelabz.fundoonotes.controller;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.service.NoteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 🔥 Create Note
    @PostMapping
    public ResponseEntity<Note> createNote(
            @RequestBody Note note,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(noteService.createNote(note, token));
    }

    // 🔥 Get Notes
    @GetMapping
    public ResponseEntity<List<Note>> getNotes(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(noteService.getNotes(token));
    }
}