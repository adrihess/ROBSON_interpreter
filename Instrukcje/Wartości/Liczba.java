package Instrukcje.Wartości;

import Instrukcje.Instrukcja;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("Liczba")
public class Liczba extends Instrukcja {
    @JsonProperty("wartosc")
    private double wartosc;
    @JsonCreator
    Liczba() {
        super();
    }

    Liczba(
            @JsonProperty("wartosc") final double wartosc)
    {

        this.wartosc = requireNonNull(wartosc);


    }
    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        if(czyOstatni)
            zapis.print("return ");
        String war = String.valueOf(wartosc);
        zapis.print(war);

    }
}
