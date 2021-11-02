package Instrukcje.DziałaniaDwuargumentowe;

import Instrukcje.Instrukcja;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;



@JsonTypeName("Dzielenie")
public class Dzielenie extends Arytmetyka {


    @JsonCreator
    Dzielenie(
         @JsonProperty("argument1") final Instrukcja argument1,
         @JsonProperty("argument2") final Instrukcja argument2)
    {

        super(argument1, argument2);
    }

    @Override
    public void wypisz(PrintWriter zapis , boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        if(czyOstatni) {
            zapis.print("return ");
            czyOstatni=false;}

        getArgument1().wypisz(zapis, false, zmienne, czyOstatni);
        zapis.print(" / ");
        getArgument2().wypisz(zapis, false, zmienne, czyOstatni);

        if(czy)
            zapis.print(";\n");
    }
}
