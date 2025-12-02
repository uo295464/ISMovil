package es.uniovi.converter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import es.uniovi.converter.Models.RetrofitClient
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var euroToDollar = 1.16
    lateinit var editTextEuros: EditText
    lateinit var editTextDollars: EditText
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editTextEuros = findViewById(R.id.editTextEuros)
        editTextDollars = findViewById(R.id.editTextDollars)
        viewModel.fetchExchangeRate()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
    fun onClickToDollars(view: android.view.View){
        Toast.makeText(this, "¡Me han pulsado!", Toast.LENGTH_SHORT).show()
        val factor = viewModel.euroToDollar
        convert(editTextEuros,editTextDollars,factor)
    }

    fun onClickToEuros(view: android.view.View){
        Toast.makeText(this, "¡Me han pulsado!", Toast.LENGTH_SHORT).show()
        val factor = viewModel.euroToDollar
        convert(editTextDollars,editTextEuros,1/factor)
    }

    private fun convert(source: EditText, destination: EditText, factor: Double) {
        val text = source.text.toString()
        val value = text.toDoubleOrNull()
        if (value == null) {
            destination.setText("")
            return
        }
        destination.setText((value * factor).toString())
    }





}