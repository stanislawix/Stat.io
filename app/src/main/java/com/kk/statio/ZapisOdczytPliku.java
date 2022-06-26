package com.kk.statio;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ZapisOdczytPliku {

   static final String nazwa_pliku_z_danymi = "data.txt";

    public static void zapiszDoPliku(Integer wypite, Context context) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nazwa_pliku_z_danymi, Context.MODE_PRIVATE));
        outputStreamWriter.write(wypite.toString() + "\r\n");
        outputStreamWriter.close();
    }


    public static Integer wczytajZPliku(Context context) throws IOException, RuntimeException {
        InputStream inputStream = context.openFileInput(nazwa_pliku_z_danymi);

        if ( inputStream != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = bufferedReader.readLine();

            Integer wypite = Integer.parseInt(receiveString);
            if (linia.length != 9) {
                System.out.println("Nieprawidlowy pojazd w pliku!");
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
