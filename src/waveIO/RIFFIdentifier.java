package waveIO;

public enum RIFFIdentifier {

    RIFF("RIFF"),
    FMT("fmt "),
    DATA("data"),
    WAVE("WAVE");
    private final String name;

    private RIFFIdentifier(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
