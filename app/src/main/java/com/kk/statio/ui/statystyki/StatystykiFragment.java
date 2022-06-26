package com.kk.statio.ui.statystyki;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kk.statio.MainActivity;
import com.kk.statio.Miesiac;
import com.kk.statio.databinding.FragmentStatystykiBinding;

import java.time.YearMonth;

public class StatystykiFragment extends Fragment {

    private FragmentStatystykiBinding binding;
    MainActivity glownaAktywnosc;

    TextView wybranyPojazdNazwa;
    TextView wybranyPojazdPrzebieg;
    TextView srednieSpalanieWyjscie;
    TextView przejazdMiesiecznyWyjscie;
    TextView wydatekMiesiecznyWyjscie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatystykiViewModel statystykiViewModel =
                new ViewModelProvider(this).get(StatystykiViewModel.class);

        binding = FragmentStatystykiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        wybranyPojazdNazwa = binding.wybranyPojazdNazwa;
        wybranyPojazdPrzebieg = binding.wybranyPojazdPrzebieg;
        srednieSpalanieWyjscie = binding.srednieSpalanieWyjscie;
        przejazdMiesiecznyWyjscie = binding.przejazdMiesiecznyWyjscie;
        wydatekMiesiecznyWyjscie = binding.wydatekMiesiecznyWyjscie;

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (glownaAktywnosc != null)
            if (glownaAktywnosc.wczytanyPojazd != null)
                if (glownaAktywnosc.miesiace != null)
                    if (!glownaAktywnosc.miesiace.isEmpty()) {
                        YearMonth actualYearMonth = YearMonth.now();
                        YearMonth tempYearMonth;
                        for (int i = 0; i < glownaAktywnosc.miesiace.size(); i++) {
                            tempYearMonth = glownaAktywnosc.miesiace.get(i).getRok_miesiac();
                            if (actualYearMonth.compareTo(tempYearMonth) == 0 ? true : false) {
                                aktualizujStatystyki(glownaAktywnosc.miesiace.get(i));
                                break;
                            }
                        }
                    }
    }

    @Override
    public void onResume() {
        super.onResume();

        glownaAktywnosc = (MainActivity) getActivity();

        if (glownaAktywnosc.wczytanyPojazd != null) {
            wybranyPojazdNazwa.setText(glownaAktywnosc.wczytanyPojazd.getWyswietlana_nazwa());
            wybranyPojazdPrzebieg.setText(glownaAktywnosc.wczytanyPojazd.getPrzebieg_km() + " km");

            if (glownaAktywnosc.miesiace != null)
                if (!glownaAktywnosc.miesiace.isEmpty()) {
                    YearMonth actualYearMonth = YearMonth.now();
                    YearMonth tempYearMonth;
                    for (int i = 0; i < glownaAktywnosc.miesiace.size(); i++) {
                        tempYearMonth = glownaAktywnosc.miesiace.get(i).getRok_miesiac();
                        if (actualYearMonth.compareTo(tempYearMonth) == 0 ? true : false) {
                            aktualizujStatystyki(glownaAktywnosc.miesiace.get(i));
                            break;
                        }
                    }
                }
        }
    }

    public void aktualizujStatystyki(Miesiac m) {
        wybranyPojazdPrzebieg.setText(glownaAktywnosc.wczytanyPojazd.getPrzebieg_km() + " km");
        srednieSpalanieWyjscie.setText(m.getZatankowane_paliwo().toString() + " L");
        przejazdMiesiecznyWyjscie.setText(m.getNabity_przebieg().toString() + " km");
        wydatekMiesiecznyWyjscie.setText(m.getKoszty_paliwa().toString() + " zł");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}