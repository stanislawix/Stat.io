package com.kk.statio.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kk.statio.DodawanieAutaActivity;
import com.kk.statio.MainActivity;
import com.kk.statio.Miesiac;
import com.kk.statio.databinding.FragmentHomeBinding;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    MainActivity glownaAktywnosc;

    Button buttonNowyPojazd;
    Button buttonDodajTankowanie;
    TextView wybranyPojazdNazwa;
    TextView wybranyPojazdPrzebieg;

    EditText nowyPrzebiegWejscie;
    EditText kosztZatankowanegoPaliwaWejscie;
    EditText zatankowanePaliwoWejscie;
    EditText cenaLitraPaliwaWejscie;
    EditText dataTankowaniaWejscie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        wybranyPojazdNazwa = binding.wybranyPojazdNazwa;
        wybranyPojazdPrzebieg = binding.wybranyPojazdPrzebieg;
        nowyPrzebiegWejscie = binding.nowyPrzebiegWejscie;
        kosztZatankowanegoPaliwaWejscie = binding.kosztZatankowanegoPaliwaWejscie;
        zatankowanePaliwoWejscie = binding.zatankowanePaliwoWejscie;
        cenaLitraPaliwaWejscie = binding.cenaLitraPaliwaWejscie;
        dataTankowaniaWejscie = binding.dataTankowaniaWejscie;
        buttonNowyPojazd = binding.buttonNowyPojazd;
        buttonDodajTankowanie = binding.buttonDodajTankowanie;

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        glownaAktywnosc = (MainActivity) getActivity();

        if (glownaAktywnosc.wczytanyPojazd != null) {
            wybranyPojazdNazwa.setText(glownaAktywnosc.wczytanyPojazd.getWyswietlana_nazwa());
            wybranyPojazdPrzebieg.setText(glownaAktywnosc.wczytanyPojazd.getPrzebieg_km() + " km");
        }

        buttonNowyPojazd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(glownaAktywnosc, DodawanieAutaActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                glownaAktywnosc.startActivity(myIntent);
            }
        });

        buttonDodajTankowanie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (glownaAktywnosc.wczytanyPojazd != null) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Double zatankowane_paliwo;
                    Double cena_litra_paliwa;
                    YearMonth rok_miesiac_tankowania;

                    Integer nowy_przebieg = Integer.parseInt(nowyPrzebiegWejscie.getText().toString());
                    BigDecimal koszt_zatankowanego_paliwa = new BigDecimal(kosztZatankowanegoPaliwaWejscie.getText().toString());
                    if(!zatankowanePaliwoWejscie.getText().toString().isEmpty())
                        zatankowane_paliwo = Double.parseDouble(zatankowanePaliwoWejscie.getText().toString());
                    else if(!cenaLitraPaliwaWejscie.getText().toString().isEmpty()) {
                        cena_litra_paliwa = Double.parseDouble(cenaLitraPaliwaWejscie.getText().toString());
                        zatankowane_paliwo = Double.parseDouble(koszt_zatankowanego_paliwa.toString()) / cena_litra_paliwa;
                    } else {
                        Toast.makeText(glownaAktywnosc.getApplicationContext(), "Nie udało się dodać tankowania - uzupełnij dane o tankowaniu!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    String data_tankowania = dataTankowaniaWejscie.getText().toString();
                    try {
                        rok_miesiac_tankowania = YearMonth.parse(data_tankowania, dateTimeFormatter);
                    } catch (DateTimeParseException e) {
                        e.printStackTrace();
                        Toast.makeText(glownaAktywnosc.getApplicationContext(), "Nie udało się dodać tankowania - data źle wpisana!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Integer nabity_przebieg = nowy_przebieg - glownaAktywnosc.wczytanyPojazd.getPrzebieg_km();
                    boolean dodane = false;

                    for(int i=0; i<glownaAktywnosc.miesiace.size(); i++) {
                        if(!dodane && glownaAktywnosc.miesiace.get(i).getRok_miesiac().compareTo(rok_miesiac_tankowania) == 0 ? true : false) {
                            glownaAktywnosc.wczytanyPojazd.dodajPrzebieg(nabity_przebieg);
                            glownaAktywnosc.miesiace.get(i).dodajPrzebieg(nabity_przebieg);
                            glownaAktywnosc.miesiace.get(i).dodajZatankowanePaliwo(zatankowane_paliwo);
                            glownaAktywnosc.miesiace.get(i).dodajKosztyPaliwa(koszt_zatankowanego_paliwa);
                            Toast.makeText(glownaAktywnosc.getApplicationContext(), "Dodano tankowanie o wartości " + koszt_zatankowanego_paliwa.toString() + " zł!", Toast.LENGTH_LONG).show();
                            dodane = true;
                            break;
                        }
                    }

                    if(!dodane) {
                        Miesiac miesiac = new Miesiac(
                                nabity_przebieg,
                                koszt_zatankowanego_paliwa,
                                zatankowane_paliwo,
                                rok_miesiac_tankowania
                        );
                        glownaAktywnosc.wczytanyPojazd.dodajPrzebieg(nabity_przebieg);

                        glownaAktywnosc.miesiace.add(miesiac);
                        Toast.makeText(glownaAktywnosc.getApplicationContext(), "Dodano tankowanie!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(glownaAktywnosc.getApplicationContext(), "Najpierw dodaj pojazd!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}