package Instrukcje;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("While")
public class While extends Instrukcja {
    @JsonProperty("blok")
    private final Blok blok;
    @JsonProperty("warunek")
    private final Instrukcja warunek;

    @JsonCreator
    While(
            @JsonProperty("blok") final Blok blok,
            @JsonProperty("warunek") final Instrukcja warunek)
    {


        this.blok = requireNonNull(blok);
        this.warunek = requireNonNull(warunek);

    }
    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        zapis.print(" while (");
        warunek.wypisz(zapis, false, zmienne, false);
        zapis.print(" ) {\n");
        blok.przechodz(zapis, zmienne, czyOstatni);
        zapis.print("}\n");

    }

}
