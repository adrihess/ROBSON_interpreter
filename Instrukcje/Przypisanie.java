package Instrukcje;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("Przypisanie")
public class Przypisanie extends Instrukcja {
    @JsonProperty("nazwa")
    private final String nazwa;
    @JsonProperty("wartosc")
    private final Instrukcja wartosc;


    @JsonCreator
    Przypisanie(
            @JsonProperty("nazwa") final String nazwa,
            @JsonProperty("wartosc") final Instrukcja wartosc)
    {


        this.nazwa = requireNonNull(nazwa);
        this.wartosc = requireNonNull(wartosc);

    }
    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        if(!zmienne.contains(nazwa)) {
            zapis.print("double ");
            zmienne.add(nazwa);
        }
        zapis.print(nazwa);
        zapis.print(" = ");
        wartosc.wypisz(zapis, false, zmienne, false);
        zapis.print(";\n");

    }

}
