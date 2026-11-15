package fm.bartrack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.round


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnOpenHistory = findViewById<Button>(R.id.btnOpenHistory)
        val btnBack = findViewById<Button>(R.id.btnBackFromProfile)
        val btnCalculate = findViewById<Button>(R.id.btnCalculateCalories)
        val etDogWeight= findViewById<EditText>(R.id.etDogWeight)
        val etDogName = findViewById<EditText>(R.id.etDogName)
        val tvCalorieResult = findViewById<TextView>(R.id.tvCalorieResult)
        val tvDogSummary = findViewById<TextView>(R.id.tvDogSummary)

        //Obliczanie zapotrzebowania energetycznego psa i wyswietlanie profilu psa
        btnCalculate.setOnClickListener {
            val dogName = etDogName.text.toString().trim()
            val weightText = etDogWeight.text.toString().replace(",",".")
            val weight = weightText.toDoubleOrNull()

            if (weight != null && weight > 0.0){
                val calories = calculateRER(weight)

                tvCalorieResult.text = "Zapotrzebowanie: $calories kcal/dzień"
                if (dogName.isNotEmpty()){
                    tvDogSummary.text = "Pies: $dogName, Waga: $weight kg"
                }
            }else {
                tvCalorieResult.text = "Zapotrzebowanie: 0.0 kcal/dzień"
                tvDogSummary.text = "Profil psa: podaj poprawną wagę"
                Toast.makeText(this, "Podaj prawidłową wagę psa w kg", Toast.LENGTH_SHORT).show()
            }
        }

        //Przejscie do HistoryActivity
        btnOpenHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
            finish()
        }

        //Powrót do menu głównego
        btnBack.setOnClickListener {
            finish()
        }
    }

    /**
     * Funkcja obliczająca dzienne zapotrzebowanie energetyczne psa (DER)
     * na podstawie wzoru spoczynkowego (RER = 70 * masaPsa^0.75 * 1.6).
     */
    private fun calculateRER(weightKg: Double): Double {
        val rawCalories = 70 * weightKg.pow(0.75) * 1.6
        return round(rawCalories * 10) / 10.0
    }
}