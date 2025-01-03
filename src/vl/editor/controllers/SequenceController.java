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

    public SequenceController() {
        this(new SequenceModel(), new SequenceViewMinimized(null));
    }

    public SequenceController(SequenceModel sequence, SequenceViewMinimized view) {
        this.sequenceModel = sequence;
        this.view = view;
        this.view.setSequenceModel(sequence); // Ensure the view has access to the model
        this.modal = new SequencerModal();
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

    public List<Note> getNotesAtRow(int row) {
        return sequenceModel.getNotes().stream()
                .filter(note -> note.getNote() == row)
                .collect(Collectors.toList());
    }

    public void launchModal() {
        modal.setVisible(true);
    }

    public void launchModal(int tickCount) {
        modal.setTickCount(tickCount);
        modal.setVisible(true);
    }

    public Function<SequenceController, Void> getOnModalClose() {
        return onModalClose;
    }

    public void setOnModalClose(Function<SequenceController, Void> onModalClose) {
        this.onModalClose = onModalClose;
    }

    public void onModalClosing() {
        onModalClose.apply(this);
    }

    public void setInstrumentID(int instrumentID) {
        sequenceModel.setInstrumentID(instrumentID);
    }
}