package vl.common;

import java.awt.*;
import java.util.Map;

public class VLConstants {
    public static final String PROJECT_FILE_EXTENSION = ".vl";
    public static final String SETTINGS_FILE_NAME = STR."settings\{PROJECT_FILE_EXTENSION}";
    public static final String DEFAULT_PROJECT_PATH = STR."default\{PROJECT_FILE_EXTENSION}";
    public static final String WINDOW_ICON_PATH = "res/icon.png";

    public static final Color TEXT_COLOR = Color.WHITE;
    public static final Color BUTTON_COLOR = Color.GRAY;
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    public static final Map<String, Integer> INSTRUMENT_TO_ID = Map.<String, Integer>ofEntries(
            Map.entry("piano1", 0),
            Map.entry("piano2", 1),
            Map.entry("piano3", 2),
            Map.entry("honky-tonk", 3),
            Map.entry("e.piano1", 4),
            Map.entry("e.piano2", 5),
            Map.entry("harpsichord", 6),
            Map.entry("clav.", 7),
            Map.entry("celesta", 8),
            Map.entry("glockenspiel", 9),
            Map.entry("musicbox", 10),
            Map.entry("vibraphone", 11),
            Map.entry("marimba", 12),
            Map.entry("xylophone", 13),
            Map.entry("tubular-bell", 14),
            Map.entry("santur", 15),
            Map.entry("organ1", 16),
            Map.entry("organ2", 17),
            Map.entry("organ3", 18),
            Map.entry("churchorg.1", 19),
            Map.entry("reedorgan", 20),
            Map.entry("accordionfr", 21),
            Map.entry("harmonica", 22),
            Map.entry("bandoneon", 23),
            Map.entry("nylon-str.gt", 24),
            Map.entry("steel-str.gt", 25),
            Map.entry("jazzgt.", 26),
            Map.entry("cleangt.", 27),
            Map.entry("mutedgt.", 28),
            Map.entry("overdrivegt", 29),
            Map.entry("distortiongt", 30),
            Map.entry("gt.harmonics", 31),
            Map.entry("acousticbs.", 32),
            Map.entry("fingeredbs.", 33),
            Map.entry("pickedbs.", 34),
            Map.entry("fretlessbs.", 35),
            Map.entry("slapbass1", 36),
            Map.entry("slapbass2", 37),
            Map.entry("synthbass1", 38),
            Map.entry("synthbass2", 39),
            Map.entry("violin", 40),
            Map.entry("viola", 41),
            Map.entry("cello", 42),
            Map.entry("contrabass", 43),
            Map.entry("tremolostr", 44),
            Map.entry("pizzicatostr", 45),
            Map.entry("harp", 46),
            Map.entry("timpani", 47),
            Map.entry("strings", 48),
            Map.entry("slowstrings", 49),
            Map.entry("syn.strings1", 50),
            Map.entry("syn.strings2", 51),
            Map.entry("choiraahs", 52),
            Map.entry("voiceoohs", 53),
            Map.entry("synvox", 54),
            Map.entry("orchestrahit", 55),
            Map.entry("trumpet", 56),
            Map.entry("trombone", 57),
            Map.entry("tuba", 58),
            Map.entry("mutedtrumpet", 59),
            Map.entry("frenchhorns", 60),
            Map.entry("brass1", 61),
            Map.entry("synthbrass1", 62),
            Map.entry("synthbrass2", 63),
            Map.entry("sopranosax", 64),
            Map.entry("altosax", 65),
            Map.entry("tenorsax", 66),
            Map.entry("baritonesax", 67),
            Map.entry("oboe", 68),
            Map.entry("englishhorn", 69),
            Map.entry("bassoon", 70),
            Map.entry("clarinet", 71),
            Map.entry("piccolo", 72),
            Map.entry("flute", 73),
            Map.entry("recorder", 74),
            Map.entry("panflute", 75),
            Map.entry("bottleblow", 76),
            Map.entry("shakuhachi", 77),
            Map.entry("whistle", 78),
            Map.entry("ocarina", 79),
            Map.entry("squarewave", 80),
            Map.entry("sawwave", 81),
            Map.entry("syn.calliope", 82),
            Map.entry("chifferlead", 83),
            Map.entry("charang", 84),
            Map.entry("solovox", 85),
            Map.entry("5thsawwave", 86),
            Map.entry("bass&lead", 87),
            Map.entry("fantasia", 88),
            Map.entry("warmpad", 89),
            Map.entry("polysynth", 90),
            Map.entry("spacevoice", 91),
            Map.entry("bowedglass", 92),
            Map.entry("metalpad", 93),
            Map.entry("halopad", 94),
            Map.entry("sweeppad", 95),
            Map.entry("icerain", 96),
            Map.entry("soundtrack", 97),
            Map.entry("crystal", 98),
            Map.entry("atmosphere", 99),
            Map.entry("brightness", 100),
            Map.entry("goblin", 101),
            Map.entry("echodrops", 102),
            Map.entry("startheme", 103),
            Map.entry("sitar", 104),
            Map.entry("banjo", 105),
            Map.entry("shamisen", 106),
            Map.entry("koto", 107),
            Map.entry("kalimba", 108),
            Map.entry("bagpipe", 109),
            Map.entry("fiddle", 110),
            Map.entry("shanai", 111),
            Map.entry("tinklebell", 112),
            Map.entry("agogo", 113),
            Map.entry("steeldrums", 114),
            Map.entry("woodblock", 115),
            Map.entry("taiko", 116),
            Map.entry("melo.tom1", 117),
            Map.entry("synthdrum", 118),
            Map.entry("reversecym.", 119),
            Map.entry("gt.fretnoise", 120),
            Map.entry("breathnoise", 121),
            Map.entry("seashore", 122),
            Map.entry("bird", 123),
            Map.entry("telephone1", 124),
            Map.entry("helicopter", 125),
            Map.entry("applause", 126),
            Map.entry("gunshot", 127),
            Map.entry("synthbass101", 38),
            Map.entry("trombone2", 57),
            Map.entry("fr.horn2", 60),
            Map.entry("square", 80),
            Map.entry("saw", 81),
            Map.entry("synmallet", 98),
            Map.entry("echobell", 102),
            Map.entry("sitar2", 104),
            Map.entry("gt.cutnoise", 120),
            Map.entry("fl.keyclick", 121),
            Map.entry("rain", 122),
            Map.entry("dog", 123),
            Map.entry("telephone2", 124),
            Map.entry("car-engine", 125),
            Map.entry("laughing", 126),
            Map.entry("machinegun", 127),
            Map.entry("echopan", 102),
            Map.entry("stringslap", 120),
            Map.entry("thunder", 122),
            Map.entry("horse-gallop", 123),
            Map.entry("doorcreaking", 124),
            Map.entry("car-stop", 125),
            Map.entry("screaming", 126),
            Map.entry("lasergun", 127),
            Map.entry("wind", 122),
            Map.entry("bird2", 123),
            Map.entry("door", 124),
            Map.entry("car-pass", 125),
            Map.entry("punch", 126),
            Map.entry("explosion", 127),
            Map.entry("stream", 122),
            Map.entry("scratch", 124),
            Map.entry("car-crash", 125),
            Map.entry("heartbeat", 126),
            Map.entry("bubble", 122),
            Map.entry("windchimes", 124),
            Map.entry("siren", 125),
            Map.entry("footsteps", 126),
            Map.entry("train", 125),
            Map.entry("jetplane", 125),
            Map.entry("detunedep1", 4),
            Map.entry("detunedep2", 5),
            Map.entry("coupledhps.", 6),
            Map.entry("churchbell", 14),
            Map.entry("detunedor.1", 16),
            Map.entry("detunedor.2", 17),
            Map.entry("churchorg.2", 19),
            Map.entry("accordionit", 21),
            Map.entry("ukulele", 24),
            Map.entry("12-str.gt", 25),
            Map.entry("hawaiiangt.", 26),
            Map.entry("chorusgt.", 27),
            Map.entry("funkgt.", 28),
            Map.entry("feedbackgt.", 30),
            Map.entry("gt.feedback", 31),
            Map.entry("synthbass3", 38),
            Map.entry("synthbass4", 39),
            Map.entry("slowviolin", 40),
            Map.entry("orchestra", 48),
            Map.entry("syn.strings3", 50),
            Map.entry("brass2", 61),
            Map.entry("synthbrass3", 62),
            Map.entry("synthbrass4", 63),
            Map.entry("sinewave", 80),
            Map.entry("doctorsolo", 81),
            Map.entry("taishokoto", 107),
            Map.entry("castanets", 115),
            Map.entry("concertbd", 116),
            Map.entry("melo.tom2", 117),
            Map.entry("808tom", 118),
            Map.entry("starship", 125),
            Map.entry("carillon", 14),
            Map.entry("elecperc.", 118),
            Map.entry("burstnoise", 125),
            Map.entry("piano1d", 0),
            Map.entry("e.piano1v", 4),
            Map.entry("e.piano2v", 5),
            Map.entry("60'sorgan1", 16),
            Map.entry("churchorg.3", 19),
            Map.entry("nylongt.o", 24),
            Map.entry("mandolin", 25),
            Map.entry("funkgt.2", 28),
            Map.entry("rubberbass", 39),
            Map.entry("analogbrass1", 62),
            Map.entry("analogbrass2", 63),
            Map.entry("60'se.piano", 4),
            Map.entry("harpsi.o", 6),
            Map.entry("organ4", 16),
            Map.entry("organ5", 17),
            Map.entry("nylongt.2", 24),
            Map.entry("choiraahs2", 52),
            Map.entry("standard", 0),
            Map.entry("room", 8),
            Map.entry("power", 16),
            Map.entry("electronic", 24),
            Map.entry("tr-808", 25),
            Map.entry("jazz", 32),
            Map.entry("brush", 40),
            Map.entry("sfx", 56)
    );
}
