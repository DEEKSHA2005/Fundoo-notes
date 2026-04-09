package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final NoteRepository noteRepository;

    public ReminderScheduler(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void checkReminders() {

        System.out.println("Checking reminders...");

        List<Note> notes = noteRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for (Note note : notes) {

            if (note.getReminderTime() != null &&
                    note.getReminderTime().isBefore(now)) {

                System.out.println("Reminder Triggered: " + note.getTitle());
                System.out.println("Sending notification...");
                System.out.println("Reminder: " + note.getDescription());

                note.setReminderTime(null);
                noteRepository.save(note);
            }
        }
    }
}