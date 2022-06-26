package com.kk.statio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kk.statio.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public Pojazd wczytanyPojazd;
    public ArrayList<Miesiac> miesiace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_statystyki)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        wczytanyPojazd = null;
        miesiace = new ArrayList<Miesiac>();

        wczytajPojazd();
        wczytajMiesiace();
    }

    @Override
    protected void onStart() {
        super.onStart();
        wczytajPojazd();
        wczytajMiesiace();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        wczytajPojazd();
        wczytajMiesiace();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wczytanyPojazd != null) {
            zapiszPojazd();
        }

        if (!miesiace.isEmpty()) {
            zapiszMiesiace();
        }

    }

    private void wczytajPojazd() {
        try {
            wczytanyPojazd = PrzechowywanieDanych.wczytajPojazd(getApplicationContext());
        } catch (RuntimeException e) {
            System.out.println("Brak lub uszkodzony plik pojazdu: " + e.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odnaleźć pliku: " + e.toString());
        } catch (IOException e) {
            System.out.println("Nie udało się czytać z pliku: " + e.toString());
        }
    }

    private void zapiszPojazd() {
        try {
            PrzechowywanieDanych.zapiszPojazd(wczytanyPojazd, getApplicationContext());
        } catch (IOException e) {
            System.out.println("Nie udało się czytać z pliku: " + e.toString());
        }
    }

    private void wczytajMiesiace() {
        try {
            miesiace = PrzechowywanieDanych.wczytajMiesiace(getApplicationContext());
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odnaleźć pliku: " + e.toString());
        } catch (IOException e) {
            System.out.println("Nie udało się czytać z pliku: " + e.toString());
        }
    }

    private void zapiszMiesiace() {
        try {
            PrzechowywanieDanych.zapiszMiesiace(miesiace, getApplicationContext());
        } catch (IOException e) {
            System.out.println("Nie udało się czytać z pliku: " + e.toString());
        }
    }

}