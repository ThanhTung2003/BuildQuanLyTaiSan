package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quanlytaisanbt2.UI.formatMoney

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("baitap2", "onCreate")

        val personList = mutableListOf<Person>()
        val assetList = mutableListOf<Asset>()
        var currentPerson:Person? = null


        val radioGroupPeopleAndAsset = findViewById<RadioGroup>(R.id.radioGroupPeopleAndAsset)
        val layoutPeople = findViewById<LinearLayout>(R.id.layoutPeople)
        val layoutAssets = findViewById<LinearLayout>(R.id.layoutAssets)
        val editTextPeople = findViewById<EditText>(R.id.editTextPeople)
        val buttonAddPeople = findViewById<Button>(R.id.buttonAddPeople)
        val editTextAsset = findViewById<EditText>(R.id.editTextAsset)
        val editTextValueAsset = findViewById<EditText>(R.id.editTextValueAsset)
        val buttonAddAsset = findViewById<Button>(R.id.buttonAddAsset)

        layoutPeople.visibility = View.VISIBLE
        layoutAssets.visibility = View.GONE
        Log.d("baitap2", "View mặc định")

        radioGroupPeopleAndAsset.setOnCheckedChangeListener { _, checkid ->
            when (checkid) {
                R.id.radiobuttonPeople -> {
                    layoutPeople.visibility = View.VISIBLE
                    layoutAssets.visibility = View.GONE
                    Log.d("baitap2", "View con người")
                }

                R.id.radiobuttonAsset -> {
                    layoutPeople.visibility = View.GONE
                    layoutAssets.visibility = View.VISIBLE
                    Log.d("baitap2", "View tài sản")
                }
            }
        }

        buttonAddPeople.setOnClickListener {
            val personName = editTextPeople.text.toString()
            if (personName.isNotEmpty()) {
                val newPerson = Person(personName)
                personList.add(newPerson)
                currentPerson = newPerson
                Log.d("baitap2", "Đã thêm con người: $personName")
            } else {
                Log.d("baitap2", "Vui lòng nhập tên con người")
            }
            editTextPeople.setText("")
        }

        buttonAddAsset.setOnClickListener {
            val assetName = editTextAsset.text.toString()
            val assetValue = editTextValueAsset.text.toString().toLongOrNull()

            if (assetName.isNotEmpty() && assetValue != null) {
                val newAsset = Asset(assetName, assetValue)

                // Kiểm tra xem có người hiện tại để thêm tài sản hay không
                currentPerson?.let { person ->
                    // Thêm tài sản vào người hiện tại
                    person.addAsset(newAsset)
                    Log.d("baitap2", "Đã thêm tài sản: $assetName : ${assetValue.formatMoney()} cho ${person.getName()}")
                } ?: Log.d("baitap2", "Không có người để thêm tài sản")
            } else {
                Log.d("baitap2", "Vui lòng nhập tên và giá trị tài sản hợp lệ")
            }
            editTextAsset.setText("")
            editTextValueAsset.setText("")
        }



        findViewById<TextView>(R.id.personResult).setOnClickListener {
            Log.d("baitap2", "- Thống kê danh sách đối tượng:")

            assetList.forEach { assets ->
                Log.d("baitap2", "${assets.getInfoStatistical()}")
            }
            personList.forEach { person ->
                Log.d("baitap2", "${person.getInfoStatistical()}")
            }

        }

        findViewById<TextView>(R.id.assetResults).setOnClickListener {
            Log.d("baitap2", "- Thống kê danh sách đối tượng:")

            // In ra danh sdách con người
            personList.forEach { person ->
                Log.d("baitap2", "+ ${person.getName()}: Con người")
            }

            // In ra danh sách tài sản
            assetList.forEach { asset ->
                Log.d("baitap2", "+ ${asset.getName()}: Tài sản")
            }

        }
    }
}