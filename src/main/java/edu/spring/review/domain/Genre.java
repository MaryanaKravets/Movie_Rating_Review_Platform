package edu.spring.review.domain;

import com.fasterxml.jackson.annotation.*;

public enum Genre {
    @JsonProperty("COMEDY")
        COM,
    @JsonProperty("ROMANCE")
        ROM,
    @JsonProperty("ACTION")
        ACT,
    @JsonProperty("CRIME")
        CR,
    @JsonProperty("DRAMA")
        DR,
    @JsonProperty("FANTASY")
        FANT
}
