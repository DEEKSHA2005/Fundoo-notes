package com.bridgelabz.fundoonotes.controller;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;

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
            @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(noteService.createNote(dto, token));
    }

    // 🔥 Get Notes
    @GetMapping
    public ResponseEntity<List<Note>> getNotes(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(noteService.getNotes(token));
    }
}