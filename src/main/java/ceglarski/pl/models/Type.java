package ceglarski.pl.models;

public enum Type {
    unknown(0), email(1), phone(2), jabber(3);

    private int type;

    Type(int type) {
        this.type = type;
    }

    public int getValue() {
        return type;
    }

}
