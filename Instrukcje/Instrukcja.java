package Instrukcje;


import Instrukcje.DziałaniaDwuargumentowe.Arytmetyka;
import Instrukcje.DziałaniaDwuargumentowe.Logika;
import Instrukcje.DziałaniaDwuargumentowe.Porownanie;
import Instrukcje.Wartości.False;
import Instrukcje.Wartości.Liczba;
import Instrukcje.Wartości.True;
import Instrukcje.Wartości.Zmienna;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typ") @JsonSubTypes({
        @JsonSubTypes.Type(value = Arytmetyka.class, name = "Arytmetyka"),
        @JsonSubTypes.Type(value = Logika.class, name = "Logika"),
        @JsonSubTypes.Type(value = Porownanie.class, name = "Porownanie"),

        @JsonSubTypes.Type(value = Zmienna.class, name = "Zmienna"),
        @JsonSubTypes.Type(value = Liczba.class, name = "Liczba"),

        @JsonSubTypes.Type(value = Przypisanie.class, name = "Przypisanie"),
        @JsonSubTypes.Type(value = Blok.class, name = "Blok"),
        @JsonSubTypes.Type(value = While.class, name = "While"),
        @JsonSubTypes.Type(value = If.class, name = "If"),

        @JsonSubTypes.Type(value = False.class, name = "False"),
        @JsonSubTypes.Type(value = True.class, name = "True"),


})
@JsonTypeName("Instrukcja")
public abstract class Instrukcja {
    public Instrukcja() {

    }

    abstract public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne , boolean czyOstatni);

}
