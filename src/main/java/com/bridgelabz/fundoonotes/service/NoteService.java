package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.dto.request.NoteRequestDto;

import java.util.List;

public interface NoteService {

    Note createNote(NoteRequestDto dto, String token);

    List<Note> getNotes(String token);
}