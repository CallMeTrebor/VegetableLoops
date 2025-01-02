package vl.controllers;

import vl.models.Note;
import vl.models.SequenceModel;
import vl.views.SequenceViewMinimized;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class SequenceController {
    private final SequenceModel sequenceModel;
    private final SequenceViewMinimized view;

    public SequenceController() {
        this(new SequenceModel(), new SequenceViewMinimized(new SequenceModel()));
    }

    public SequenceController(SequenceModel sequence, SequenceViewMinimized view) {
        this.sequenceModel = sequence;
        this.view = view;
        this.view.setSequenceModel(sequence); // Ensure the view has access to the model
    }

    public void add(Note note) {
        sequenceModel.add(note);
        view.repaint(); // Update the view
    }

    public void removeNote(int index) {
        sequenceModel.removeNote(index);
        view.repaint(); // Update the view
    }

    public SequenceViewMinimized getView() {
        return view;
    }

    /**
     * Compiles the sequence to a MIDI track.
     * @param track The track to compile to.
     * @param tickOffset The tick offset to begin the sequence from.
     */
    public void compileToTrack(Track track, long tickOffset) {
        try {
            ShortMessage setInstrument = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0,
                    sequenceModel.getInstrumentID(), 0);
            track.add(new MidiEvent(setInstrument, tickOffset));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();

            // TODO: Handle exception
        }

        for (Note note : sequenceModel.getNotes()) {
            try {
                ShortMessage on = new ShortMessage(ShortMessage.NOTE_ON, 0, note.getNote(), note.getVelocity());
                ShortMessage off = new ShortMessage(ShortMessage.NOTE_OFF, 0, note.getNote(), note.getVelocity());

                track.add(new MidiEvent(on, tickOffset + note.getEntryTick()));
                track.add(new MidiEvent(off, tickOffset + note.getEntryTick() + note.getDuration()));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();

                // TODO: Handle exception
            }
        }
    }
}