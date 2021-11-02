package Glowny;
import Instrukcje.*;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


import Instrukcje.DziałaniaDwuargumentowe.*;
import Instrukcje.Wartości.False;
import Instrukcje.Wartości.Liczba;
import Instrukcje.Wartości.True;
import Instrukcje.Wartości.Zmienna;
import Wyjatek.BladWykonania;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.junit.Test;


//Ta klasa przetwarza dane z pomocą ObjectMappera
class JSON_silnik {
    public static final ObjectMapper maper = new ObjectMapper();

    static {
        //mapowanie typów
        maper.registerSubtypes(new NamedType(And.class, "And"));
        maper.registerSubtypes(new NamedType(Or.class, "Or"));

        maper.registerSubtypes(new NamedType(Plus.class, "Plus"));
        maper.registerSubtypes(new NamedType(Minus.class, "Minus"));
        maper.registerSubtypes(new NamedType(Razy.class, "Razy"));
        maper.registerSubtypes(new NamedType(Dzielenie.class, "Dzielenie"));

        maper.registerSubtypes(new NamedType(Mniejsze.class, "<"));
        maper.registerSubtypes(new NamedType(MniejszeRowne.class, "<="));
        maper.registerSubtypes(new NamedType(Wieksze.class, ">"));
        maper.registerSubtypes(new NamedType(WiekszeRowne.class, ">="));
        maper.registerSubtypes(new NamedType(Rowne.class, "=="));

        maper.registerSubtypes(new NamedType(If.class, "If"));
        maper.registerSubtypes(new NamedType(Porownanie.class, "Porownanie"));
        maper.registerSubtypes(new NamedType(Przypisanie.class, "Przypisanie"));
        maper.registerSubtypes(new NamedType(While.class, "While"));

        maper.registerSubtypes(new NamedType(Liczba.class, "Liczba"));
        maper.registerSubtypes(new NamedType(Zmienna.class, "Zmienna"));
        maper.registerSubtypes(new NamedType(True.class, "True"));
        maper.registerSubtypes(new NamedType(False.class, "False"));

        maper.registerSubtypes(new NamedType(Arytmetyka.class, "Arytmetyka"));
        maper.registerSubtypes(new NamedType(Logika.class, "Logika"));

        maper.registerSubtypes(new NamedType(Instrukcja.class, "Instrukcja"));
        maper.registerSubtypes(new NamedType(Instrukcja.class, "Porownanie"));

    }

    //Tworzy nadrzędny blok - będacy programem
    public static Blok robDrzewo(String kodJson) throws IOException {
        Blok wynik = new Blok();

        JsonNode wezely = maper.readTree(kodJson);

        przetwarzaj( wezely , wynik);

        return wynik;

    }

    //rekurencyjne parsowanie
    public static void przetwarzaj(JsonNode wezel, Blok wynikowy) throws JsonProcessingException {
        JsonNode wynik = wezel.get("instrukcje");

        if(wynik != null) {
            for(JsonNode node : wynik) {
                przetwarzaj(node, wynikowy);
            }
        }
        else {
            wynikowy.instrukcje.add(JSON_silnik.maper.treeToValue(wezel,  Instrukcja.class));
        }

    }

}

public class Robson {

    static String kodJSON;
    static Blok program;

    public static void Konwerter() throws IOException {

        program = JSON_silnik.robDrzewo(Robson.kodJSON);


    }
    public static void toJAVA(String filename) throws FileNotFoundException {
        ArrayList<String> zmienne = new ArrayList<>();

        File plik = new File("java.java");
        PrintWriter zapis = new PrintWriter(plik);
        zapis.print("import java.io.File;\n"+
                "import java.io.FileNotFoundException;;\n" +
                "import java.io.PrintWriter;\n\n"+
                "public class java {\n" +
                "public static void main(String[] args) throws FileNotFoundException { \n " +
                "double wynik = java.wykonaj();\n" +
                "File plik = new File(\"wynik.txt\");\n "+
                "PrintWriter zapis = new PrintWriter(\"wynik.txt\");\n" +
                "zapis.print(wynik);\n"+
                "zapis.close();}\n\n"+
                "public static double wykonaj() {");

        program.przechodz2(zapis, zmienne);
        zapis.print("\t}\n }");
        zapis.close();
    }

    public static double wykonaj() throws IOException, InterruptedException, BladWykonania {
	//Tutaj miało być uruchomienie wyprodukowanego programu Javy z Robson, ale nie działa
       // new ProcessBuilder("java", "-classpath", "\\\\wsl$\\Ubuntu-20.04\\home\\mint\\PO2", "java.java");
       Scanner wczyt = new Scanner(System.in);
        System.out.println("----------- Skompiluj java.java z konsola ---------");
        Thread.sleep(1000);
        System.out.println("----------- Wczyśnij enter żeby kontynować ---------");
        String t = wczyt.nextLine();
        while(t.length() > 0) {
            t = wczyt.nextLine();
        }

        File plik = new File("wynik.txt");
        if(!plik.exists()) {
            throw new BladWykonania();
        }


        Scanner scan = new Scanner(plik);
        String str = scan.nextLine();

        double wynik = Double.parseDouble(str);
        System.out.println("----------- Twój wynik wyświeli się poniżej ---------");
        return wynik;

    }



    public static void main(String[] args) throws IOException, InterruptedException, BladWykonania {

        //String[] argsr = {"plik.JSON", "Nowy.txt"};
        try {
            fromJSON(args[0]); }
        catch(Exception wyjatek) {
            System.exit(-1);
        }

        toJSON(args[1]);

        Konwerter();

        toJAVA("java.java");

        double wynik = wykonaj();
        System.out.println(wynik);

    }

    public static void fromJSON(String plikWczytany) throws IOException {

        kodJSON = Files.readString(Path.of(plikWczytany), StandardCharsets.UTF_8);

    }


    public static void toJSON(String plik) throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter(plik);
        zapis.println(kodJSON);
        zapis.close();
    }

}




