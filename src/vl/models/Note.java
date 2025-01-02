package vl.models;

public class Note {
    private int note, velocity;
    private int duration;
    private long entryTick;

    public Note() {
        this(0, 0, 0, 0);
    }

    public Note(int note, int velocity, int duration, long entryTick) {
        this.note = note;
        this.velocity = velocity;
        this.duration = duration;
        this.entryTick = entryTick;
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
}
