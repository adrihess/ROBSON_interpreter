package Instrukcje;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("If")
public class If extends Instrukcja {
    @JsonProperty("blok_prawda")
    private final Blok blok_prawda;
    @JsonProperty("blok_falsz")
    private final Blok blok_falsz;
    @JsonProperty("warunek")
    private final Instrukcja warunek;



    @JsonCreator
    If(
            @JsonProperty("blok_prawda") final Blok blok_prawda,
            @JsonProperty("blok_falsz") final Blok blok_falsz,
            @JsonProperty("warunek") final Instrukcja warunek)
    {


        this.blok_falsz = requireNonNull(blok_falsz);
        this.blok_prawda = requireNonNull(blok_prawda);
        this.warunek = requireNonNull(warunek);

    }

    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        zapis.print(" if( ");
        warunek.wypisz(zapis, false, zmienne, false);
        zapis.print(" ) {");
        blok_prawda.przechodz(zapis, zmienne, czyOstatni);
        if(czyOstatni)
            zapis.print(";");
        zapis.print(" } else { \n");
        if(blok_falsz != null)
        blok_falsz.przechodz(zapis, zmienne, czyOstatni);
        zapis.print(" }\n");


    }

}
