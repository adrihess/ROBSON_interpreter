package Instrukcje.DziałaniaDwuargumentowe;

import Instrukcje.*;
import com.fasterxml.jackson.annotation.*;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typ") @JsonSubTypes({
        @JsonSubTypes.Type(value = Mniejsze.class, name = "<"),
        @JsonSubTypes.Type(value = Wieksze.class, name = ">"),

        @JsonSubTypes.Type(value = MniejszeRowne.class, name = "<="),
        @JsonSubTypes.Type(value = WiekszeRowne.class, name = ">="),

        @JsonSubTypes.Type(value = Rowne.class, name = "=="),

})
@JsonTypeName("Porownanie")
public abstract class Porownanie extends Instrukcja {
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

    public Porownanie() {
        super();
    }


    public Porownanie(Instrukcja jed, Instrukcja dwa) {

        this.argument1 = requireNonNull(jed);
        this.argument2 = requireNonNull(dwa);
    }

    abstract public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni);



}