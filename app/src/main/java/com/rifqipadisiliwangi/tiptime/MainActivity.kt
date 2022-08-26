package com.rifqipadisiliwangi.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    fun setupListener(){
        val et_cost_service = findViewById<EditText>(R.id.et_cost_service)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val swRound = findViewById<Switch>(R.id.swRound)
        val rgService_rules = findViewById<RadioGroup>(R.id.rgService_rules)
        val tvAmount = findViewById<TextView>(R.id.tvAmount)
        var rbService : RadioButton?= null

        rgService_rules.setOnCheckedChangeListener{radioGroup, i ->
            rbService = findViewById(i)
        }

        btnCalculate.setOnClickListener{
            var total= calculateTip(et_cost_service.text.toString().toDouble(), rbService?.text.toString())
            if (swRound.isChecked){
                total = total.roundToInt().toDouble()
            }
            tvAmount.text ="Total : $total"
            AlertDialog.Builder(this).setTitle("Tip").setMessage("Total : $total").setNeutralButton("OK", null).show()
        }

    }

    fun calculateTip(cost : Double, service : String): Double{
        val tip = when(service){
            "Amazing (20%)" ->cost *0.2
            "Good (18%)" ->cost *0.18
            "Ok (15%)" ->cost *0.15
            else -> 0.0
        }
        return tip
    }
}