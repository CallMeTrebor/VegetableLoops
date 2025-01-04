package vl.editor.controllers;

import vl.editor.models.Note;
import vl.editor.models.SequenceModel;
import vl.editor.views.SequenceViewMinimized;
import vl.modals.views.SequencerModal;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SequenceController {
    private final SequenceModel sequenceModel;
    private final SequenceViewMinimized view;
    private final SequencerModal modal;

    private Function<SequenceController, Void> onModalClose;
    private Function<Note, Void> onNoteChanged;

    public SequenceController(int ticks) {
        this(new SequenceModel(0, ticks), new SequenceViewMinimized(null), ticks);
    }

    public SequenceController(SequenceModel sequence, SequenceViewMinimized view, int ticks) {
        this.sequenceModel = sequence;
        this.view = view;
        this.view.setSequenceModel(sequence); // Ensure the view has access to the model
        this.modal = new SequencerModal(ticks);
        this.modal.setSequenceController(this);

        this.modal.setOnNoteAdd(this::addNote);
        this.modal.setOnNoteRemove(this::removeNote);
    }

    public Void addNote(Note note) {
        sequenceModel.add(note);
        if(onNoteChanged != null) onNoteChanged.apply(note);
        view.repaint(); // Update the view

        return null;
    }

    public Void removeNote(int index) {
        sequenceModel.removeNote(index);
        if(onNoteChanged != null) onNoteChanged.apply(null);
        view.repaint(); // Update the view

        return null;
    }

    public Void removeNote(Note note) {
        sequenceModel.removeNote(note);
        if(onNoteChanged != null) onNoteChanged.apply(null);
        view.repaint(); // Update the view

        return null;
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

    public List<Note> getNotesAtRow(int row) {
        return sequenceModel.getNotes().stream()
                .filter(note -> note.getNote() == row)
                .collect(Collectors.toList());
    }

    public void launchModal() {
        modal.setVisible(true);
    }

    public void launchModal(int tickNumber) {
        modal.setTickCount(tickNumber);
        modal.setVisible(true);
    }

    public Function<SequenceController, Void> getOnModalClose() {
        return onModalClose;
    }

    public void setOnModalClose(Function<SequenceController, Void> onModalClose) {
        this.onModalClose = onModalClose;
    }

    public void onModalClosing() {
        if (onModalClose != null) onModalClose.apply(this);
    }

    public void setInstrumentID(int instrumentID) {
        sequenceModel.setInstrumentID(instrumentID);
    }

    public SequenceModel getSequenceModel() {
        return sequenceModel;
    }

    public Function<Note, Void> getOnNoteChanged() {
        return onNoteChanged;
    }

    public void setOnNoteChanged(Function<Note, Void> onNoteChanged) {
        this.onNoteChanged = onNoteChanged;
    }

    public int getDuration() {
        return sequenceModel.getNotes().stream()
                .mapToInt(note -> Math.toIntExact(note.getEntryTick() + note.getDuration()))
                .max()
                .orElse(0);
    }

    public int getTicks() {
        return sequenceModel.getTicks();
    }

    public void setTicks(int ticks) {
        sequenceModel.setTicks(ticks);
    }
}