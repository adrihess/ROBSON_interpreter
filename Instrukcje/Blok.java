package Instrukcje;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@JsonTypeName("Blok")
public class Blok extends Instrukcja {
    @JsonProperty("instrukcje")
    public ArrayList<Instrukcja> instrukcje;

    @JsonCreator
    public Blok(
            @JsonProperty("instrukcje") final ArrayList<Instrukcja> instrukcje

    ) {
        this.instrukcje=Objects.requireNonNull(instrukcje);
    }


    public Blok() {
        this.instrukcje=new ArrayList<>();
    }

    @JsonIgnore
    public void przechodz(PrintWriter zapis, ArrayList<String> zmienne, boolean czyOstatni) {

        for (Instrukcja temp : this.instrukcje) {
            if (temp instanceof Blok) {
                ((Blok) temp).przechodz(zapis, zmienne, czyOstatni);
            } else {
                temp.wypisz(zapis, true, zmienne, czyOstatni);

            }

        }
    }
    @JsonIgnore
    public void przechodz2(PrintWriter zapis, ArrayList<String> zmienne) {
        boolean lost = false;
        for(int i=0; i<this.instrukcje.size(); i++) {

            Instrukcja temp = this.instrukcje.get(i);

            if(i == this.instrukcje.size()-1)
                lost=true;
            if(temp instanceof Blok) {
                ((Blok) temp).przechodz2(zapis, zmienne);
            }
            else {
                temp.wypisz(zapis, true,  zmienne, lost);

            }

        }
    }


    public void wypisz(PrintWriter zapis, boolean czy, ArrayList<String> zmienne, boolean czyOstatni) {

    }
}
