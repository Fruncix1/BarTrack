# BarTrack

Aplikacja mobilna na Androida pisana w Kotlinie do śledzenia spacerów z psem oraz obliczania jego dziennego zapotrzebowania kalorycznego.

## Co obecnie działa (Etap 1)

* **3 aktywności z osobnymi widokami XML:**
  * `MainActivity` – ekran główny ze statusem spaceru i przyciskami nawigacji.
  * `ProfileActivity` – ekran profilu psa (wpisanie imienia, wagi oraz obliczenie zapotrzebowania kalorycznego).
  * `HistoryActivity` – ekran historii spacerów (przygotowany pod wyświetlanie zapisanych wyjść).
* **Nawigacja (`Intent`):**
  * Przejścia między wszystkimi trzema ekranami.
  * Zamykanie ekranów podrzędnych przez `finish()`, żeby nie zapychać stosu przy przełączaniu między profilem a historią.
* **Kalkulator kalorii w `ProfileActivity`:**
  * Oblicza dzienne zapotrzebowanie energetyczne psa ze wzoru `70 * waga^0.75 * 1.6` (wydzielona funkcja `calculateRER`).
  * Obsługuje zamianę przecinka na kropkę, sprawdza czy waga jest większa od zera i wyświetla komunikat `Toast` przy błędnych danych.

## Technologie

* Kotlin
* XML (`ConstraintLayout`, `ScrollView`)
* Android Studio / Gradle

## Uruchomienie projektu

1. Sklonuj repozytorium:
   ```bash
   git clone (https://github.com/Fruncix1/BarTrack.git)
2. Otwórz folder w Android Studio i poczekaj na zbudowanie projektu przez Gradle.
   
3. Uruchom na emulatorze lub telefonie z Androidem.

## Plan rozwoju
* Grudzień: dodanie stopera na MainActivity (liczenie czasu trwania spaceru, godzina wyjścia i powrotu) oraz przekazywanie wyniku do historii przez Intent.

* Styczeń: zapisywanie profilu psa i ukończonych spacerów w pamięci telefonu (SharedPreferences), żeby dane nie znikały po zamknięciu aplikacji.

* Luty: lista zapisanych spacerów oparta o RecyclerView z możliwością usuwania wpisów i podsumowaniem czasu z całego tygodnia.

* Marzec: przycisk do szybkiego wyszukiwania weterynarza w Mapach Google (niejawny Intent) oraz wybór poziomu aktywności psa w kalkulatorze kalorii.

* Kwiecień: poprawki wyglądu (karty CardView, ciemny motyw) i końcowe testy aplikacji.
