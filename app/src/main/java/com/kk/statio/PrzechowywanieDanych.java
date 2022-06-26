package com.kk.statio;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class PrzechowywanieDanych {

    static final String nazwa_pliku_z_pojazdami = "vehicles.txt";
    static final String nazwa_pliku_z_danymi = "data.txt";

    public static void zapiszMiesiace(ArrayList<Miesiac> miesiace, Context context) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nazwa_pliku_z_danymi, Context.MODE_PRIVATE));
        for (Miesiac m : miesiace) {
            outputStreamWriter.write(m.toString());
        }
        outputStreamWriter.close();
    }

    public static ArrayList<Miesiac> wczytajMiesiace(Context context) throws IOException {
        InputStream inputStream = context.openFileInput(nazwa_pliku_z_danymi);
        if ( inputStream != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            ArrayList<Miesiac> miesiace = new ArrayList<>();

            int wczytaneMiesiace = 0;
            while ((receiveString = bufferedReader.readLine()) != null && wczytaneMiesiace < 2) {
                String[] linia = receiveString.split(";");
                if (linia.length != 4) {
                    System.out.println("Nieprawidlowy miesiac nr " + (wczytaneMiesiace + 1) + "w pliku data.txt!");
                    for (int i = 0; i < linia.length; i++)
                        System.out.println("\"" + linia[i] + "\"");
                } else {
                    miesiace.add(new Miesiac(linia));
                    System.out.println(miesiace.get(miesiace.size() - 1));
                    wczytaneMiesiace++;
                }
            }

            System.out.println("Wczytano " + wczytaneMiesiace + " linie z pliku data.txt");
            inputStream.close();
            return miesiace;
        } else {
            Toast.makeText(context, "Nie udało się wczytać danych historycznych!", Toast.LENGTH_LONG).show();
            throw new RuntimeException("Nie udalo sie wczytac danych historycznych z pliku data.txt");
        }
    }

    public static void zapiszPojazd(Pojazd pojazd, Context context) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nazwa_pliku_z_pojazdami, Context.MODE_PRIVATE));
        outputStreamWriter.write(pojazd.toString());
        outputStreamWriter.close();
    }

    public static Pojazd wczytajPojazd(Context context) throws IOException, RuntimeException {
        InputStream inputStream = context.openFileInput(nazwa_pliku_z_pojazdami);
        Pojazd pojazd = new Pojazd();

        if ( inputStream != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = bufferedReader.readLine();
            String[] linia = receiveString.split(";");
            if (linia.length != 9) {
                System.out.println("Nieprawidlowy pojazd w pliku vehicles.txt!");
                for (int i = 0; i < linia.length; i++)
                    System.out.println("\"" + linia[i] + "\"");
                throw new RuntimeException("Nieprawidlowy pojazd w pliku vehicles.txt!");
            } else {
                pojazd = new Pojazd(linia);
                System.out.println("Wczytano pojazd z pliku vehicles.txt :");
                System.out.println(pojazd);
            }
        }

        inputStream.close();
        return pojazd;
    }


}
