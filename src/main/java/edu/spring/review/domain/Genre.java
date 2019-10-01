package edu.spring.review.domain;

public enum Genre {
    COM("COMEDY"),
    ROM("ROMANCE"),
    ACT("ACTION"),
    CR("CRIME"),
    DR("DRAMA"),
    FANT("FANTASY"),
    OTH("OTHER");

    Genre() {
    }

    private String genreValue;

    Genre(String genreValue) {
        this.genreValue = genreValue;
    }

    public String getGenreValue() {

        return genreValue;
    }

    public static Genre fromString(String text) {
        for (Genre b : Genre.values()) {
            if (b.getGenreValue().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return OTH;
    }
}
