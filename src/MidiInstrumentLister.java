import javax.sound.midi.*;
import java.util.HashSet;
import java.util.Set;

public class MidiInstrumentLister {
    public static void main(String[] args) {
        try {
            // Get the default synthesizer
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            // Get available instruments
            Soundbank soundbank = synthesizer.getDefaultSoundbank();
            if (soundbank != null) {
                Instrument[] instruments = soundbank.getInstruments();

                Set<String> instrumentNames = new HashSet<>();

                System.out.println("Available MIDI Instruments:");
                for (Instrument instrument : instruments) {
                    if (instrumentNames.contains(instrument.getName())) {
                        continue;
                    }
                    instrumentNames.add(instrument.getName());
                    System.out.printf("Map.entry(\"%s\", %d),%n", instrument.getName().replace(" ", "").toLowerCase(), instrument.getPatch().getProgram());
                }
            } else {
                System.out.println("No default soundbank available.");
            }

            synthesizer.close();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}