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
        @JsonSubTypes.Type(value = Plus.class, name = "Plus"),
        @JsonSubTypes.Type(value = Minus.class, name = "Minus"),

        @JsonSubTypes.Type(value = Razy.class, name = "Razy"),
        @JsonSubTypes.Type(value = Dzielenie.class, name = "Dzielenie"),

})
@JsonTypeName("Arytmetyka")
public abstract class Arytmetyka extends Instrukcja {
    @JsonProperty("argument1")
    private Instrukcja argument1;
    @JsonProperty("argument2")
    private Instrukcja argument2;
    public Arytmetyka() {
        super();
    }

    public Instrukcja getArgument1() {
        return argument1;
    }

    public Instrukcja getArgument2() {
        return argument2;
    }


    public Arytmetyka(Instrukcja jed, Instrukcja dwa) {
        this.argument1 = requireNonNull(jed);
        this.argument2 = requireNonNull(dwa);
    }
    @Override
    abstract public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni);
}
