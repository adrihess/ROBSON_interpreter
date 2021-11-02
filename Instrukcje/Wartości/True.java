package Instrukcje.Wartości;


import Instrukcje.Instrukcja;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

@JsonTypeName("True")
    public class True extends Instrukcja {


    True() {
        super();
    }


    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {
        if(czyOstatni)
            zapis.print("return ");

        zapis.print(" true ");

    }
}

