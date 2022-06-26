package com.kk.statio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class DodawanieAutaActivity extends AppCompatActivity {

    Button buttonDodajPojazd;
    TextView przebiegNaglowek;
    TextView pojemnoscBakuNaglowek;
    TextView rokProdukcjiNaglowek;
    TextView nrRejestracyjnyNaglowek;
    TextView modelNaglowek;
    TextView markaNaglowek;
    TextView nazwaNaglowek;
    EditText przebiegWejscie;
    EditText pojemnoscBakuWejscie;
    EditText rokProdukcjiWejscie;
    EditText nrRejestracyjnyWejscie;
    EditText modelWejscie;
    EditText markaWejscie;
    EditText nazwaWejscie;

    Pojazd pojazd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_auta);

        przebiegNaglowek = findViewById(R.id.przebiegNaglowek);
        pojemnoscBakuNaglowek = findViewById(R.id.pojemnoscBakuNaglowek);
        rokProdukcjiNaglowek = findViewById(R.id.rokProdukcjiNaglowek);
        nrRejestracyjnyNaglowek = findViewById(R.id.nrRejestracyjnyNaglowek);
        modelNaglowek = findViewById(R.id.modelNaglowek);
        markaNaglowek = findViewById(R.id.markaNaglowek);
        nazwaNaglowek = findViewById(R.id.nazwaNaglowek);
        przebiegWejscie = findViewById(R.id.przebiegWejscie);
        pojemnoscBakuWejscie = findViewById(R.id.pojemnoscBakuWejscie);
        rokProdukcjiWejscie = findViewById(R.id.rokProdukcjiWejscie);
        nrRejestracyjnyWejscie = findViewById(R.id.nrRejestracyjnyWejscie);
        modelWejscie = findViewById(R.id.modelWejscie);
        markaWejscie = findViewById(R.id.markaWejscie);
        nazwaWejscie = findViewById(R.id.nazwaWejscie);

        buttonDodajPojazd = findViewById(R.id.buttonDodajPojazd);
        buttonDodajPojazd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nazwa = nazwaWejscie.getText().toString();
                String marka = markaWejscie.getText().toString();
                String model = modelWejscie.getText().toString();
                String rejestracja = nrRejestracyjnyWejscie.getText().toString();
                Integer przebieg = Integer.parseInt(przebiegWejscie.getText().toString());
                Integer rokProdukcji = Integer.parseInt(rokProdukcjiWejscie.getText().toString());
                Double pojemnoscBaku = Double.parseDouble(pojemnoscBakuWejscie.getText().toString());

                pojazd = new Pojazd(1, nazwa, marka, model, rejestracja, przebieg, rokProdukcji, pojemnoscBaku);

                try {
                    PrzechowywanieDanych.zapiszPojazd(pojazd, getApplicationContext());
                } catch (IOException e) {
                    System.out.println("Can not read file: " + e.toString());
                }

                finish();
            }
        });
    }


}