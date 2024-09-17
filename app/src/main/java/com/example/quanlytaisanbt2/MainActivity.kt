package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
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
        val totalAssetsList = mutableListOf<Asset>()
        val currentPersonAssets = mutableListOf<Asset>()

        val radioGroupPeopleAndAsset = findViewById<RadioGroup>(R.id.radioGroupPeopleAndAsset)
        val layoutPeople = findViewById<LinearLayout>(R.id.layoutPeople)
        val layoutAssets = findViewById<LinearLayout>(R.id.layoutAssets)
        val editTextPeople = findViewById<EditText>(R.id.editTextPeople)
        val buttonAddPeople = findViewById<Button>(R.id.buttonAddPeople)
        val editTextAsset = findViewById<EditText>(R.id.editTextAsset)
        val editTextValueAsset = findViewById<EditText>(R.id.editTextValueAsset)
        val buttonAddAsset = findViewById<Button>(R.id.buttonAddAsset)
        val peopleListView = findViewById<ListView>(R.id.peopleListView)
        val assetListView = findViewById<ListView>(R.id.assetListView)
        val textListAssets = findViewById<TextView>(R.id.textListAssets)
        val personResult = findViewById<TextView>(R.id.personResult)
        val assetResult = findViewById<TextView>(R.id.assetResults)

        val peopleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, personList.map { it.getPersonName() })
        val assetAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assetList.map { it.getInfo() })

        peopleListView.adapter = peopleAdapter
        assetListView.adapter = assetAdapter

        layoutPeople.visibility = View.VISIBLE
        layoutAssets.visibility = View.GONE
        Log.d("baitap2", "View mặc định")

        radioGroupPeopleAndAsset.setOnCheckedChangeListener { _, checkid ->
            when (checkid) {
                R.id.radiobuttonPeople -> {
                    layoutPeople.visibility = View.VISIBLE
                    layoutAssets.visibility = View.GONE
                    Log.d("baitap2", "View Con người")

                }
                R.id.radiobuttonAsset -> {
                    layoutPeople.visibility = View.GONE
                    layoutAssets.visibility = View.VISIBLE
                    Log.d("baitap2", "View Tài sản")

                }
            }
        }

        fun updateTextListAssets() {

            val assetsInfo = currentPersonAssets.joinToString(", ") { it.getName() }
            textListAssets.text = assetsInfo
        }

        fun addAssetToCurrentPerson(asset: Asset) {

            currentPersonAssets.add(asset)

            updateTextListAssets()
        }

        fun updatePeopleAdapter() {
            peopleAdapter.clear()
            peopleAdapter.addAll(personList.map { it.getFullInfo() })
            peopleAdapter.notifyDataSetChanged()
        }

        fun savePerson(name: String) {
            if (name.isNotEmpty() && currentPersonAssets.isNotEmpty()) {
                val person = Person(name)

                currentPersonAssets.forEach { asset ->
                    person.addAsset(asset)
                }
                personList.add(person)
                updatePeopleAdapter()
                currentPersonAssets.clear()
                updateTextListAssets()
                Log.d("baitap2", "Thêm $name thành công")
            } else {
                Log.d("baitap2", "$name chưa thêm tài sản")
            }
        }

        fun updateAssetsListView() {

            val assetsInfo = assetList.map { asset -> "${asset.getName()} - ${asset.value.formatMoney()}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assetsInfo)
            assetListView.adapter = adapter
        }

        buttonAddAsset.setOnClickListener {
            val assetName = editTextAsset.text.toString()
            val assetValue = editTextValueAsset.text.toString().toLongOrNull()

            if (assetName.isNotEmpty() && assetValue != null) {
                val asset = Asset(assetName, assetValue)
                assetList.add(asset)
                assetAdapter.notifyDataSetChanged()
                addAssetToCurrentPerson(asset) // add tài sản vào người hiện tại
                editTextAsset.text.clear()
                editTextValueAsset.text.clear()
                updateAssetsListView()
                Log.d("baitap2", "Thêm $assetName thành công với giá: ${assetValue.formatMoney()}")
            } else {
                Log.d("baitap2", "Chưa điền tên hoặc giá tài sản")
            }
        }

        buttonAddPeople.setOnClickListener {
            val personName = editTextPeople.text.toString()
            savePerson(personName)
            editTextPeople.text.clear()
        }

        fun viewResult() {
            val builder = StringBuilder()
            builder.append("- Thống kê danh sách đối tượng:\n")

            // Thêm tất cả "Con người"
            personList.forEach { person ->
                builder.append(person.getInfoStatistical()).append("\n")
            }

            // Thêm tất cả "Tài sản"
            assetList.forEach { asset ->
                builder.append(asset.getInfoStatistical()).append("\n")
            }

            // Hiển thị kết quả trên Logcat
            Log.d("baitap2", builder.toString())
        }

        personResult.setOnClickListener {
            viewResult()
        }
    }
}
