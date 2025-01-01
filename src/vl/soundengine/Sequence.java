package vl.soundengine;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private final List<Note> notes = new ArrayList<>();
    private int instrumentID;

    public Sequence() {
        this(0);
    }

    public Sequence(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void removeNote(int index) {
        notes.remove(index);
    }

    public Note getNote(int index) {
        return notes.get(index);
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    /**
     * Compiles the sequence to a MIDI track.
     * @param track The track to compile to.
     * @param tickOffset The tick offset to begin the sequence from.
     */
    public void compileToTrack(Track track, long tickOffset) {
        try {
            ShortMessage setInstrument = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, instrumentID, 0);
            track.add(new MidiEvent(setInstrument, tickOffset));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();

            // TODO: Handle exception
        }

        for (Note note : notes) {
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
