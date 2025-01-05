package vl.editor.models;

public class Note {
    private int note, velocity;
    private int duration;
    private long entryTick;

    public Note(int note, int velocity, int duration, long entryTick) {
        this.note = note;
        this.velocity = velocity;
        this.duration = duration;
        this.entryTick = entryTick;
    }

    public static String getNoteName(int midiNote) {
        // Array of note names with sharps
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        int octave = (midiNote / 12) - 1;
        String note = notes[midiNote % 12];
        return note + octave;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getEntryTick() {
        return entryTick;
    }

    public void setEntryTick(long entryTick) {
        this.entryTick = entryTick;
    }

    public String serialiseToFile() {
        return String.format("%d,%d,%d,%d", note, velocity, duration, entryTick);
    }
}
