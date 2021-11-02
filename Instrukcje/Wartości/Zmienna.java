package Instrukcje.Wartości;

import Instrukcje.Instrukcja;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("Zmienna")
public class Zmienna extends Instrukcja {
    @JsonProperty("nazwa")
    private String nazwa;
    Zmienna() {
        super();
    }

    @JsonCreator
    Zmienna(

            @JsonProperty("nazwa") final String nazwa)
    {

        this.nazwa = requireNonNull(nazwa);

    }
    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        if(!zmienne.contains(nazwa)) {
            zapis.print("double ");
            zmienne.add(nazwa);
        }
        if(czyOstatni)
            zapis.print("return ");
        zapis.print(nazwa);

        if(czy)
        zapis.print(";\n");

    }
}
