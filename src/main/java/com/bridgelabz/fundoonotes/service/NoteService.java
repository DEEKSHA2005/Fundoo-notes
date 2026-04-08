package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.entity.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note, String token);

    List<Note> getNotes(String token);
}