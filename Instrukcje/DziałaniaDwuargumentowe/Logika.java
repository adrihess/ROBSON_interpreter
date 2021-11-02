package Instrukcje.DziałaniaDwuargumentowe;

import Instrukcje.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typ") @JsonSubTypes({
        @JsonSubTypes.Type(value = And.class, name = "And"),
        @JsonSubTypes.Type(value = Or.class, name = "Or"),

})
@JsonTypeName("Logika")
public abstract class Logika extends Instrukcja {
    @JsonProperty("argument1")
    private Instrukcja argument1;
    @JsonProperty("argument2")
    private Instrukcja argument2;

    public Instrukcja getArgument1() {
        return argument1;
    }

    public Instrukcja getArgument2() {
        return argument2;
    }



    Logika(Instrukcja jed, Instrukcja dwa) {

        this.argument1 = requireNonNull(jed);
        this.argument2 = requireNonNull(dwa);
    }

    abstract public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni);
}
