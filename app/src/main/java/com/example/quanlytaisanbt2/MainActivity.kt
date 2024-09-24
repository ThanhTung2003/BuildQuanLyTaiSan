package com.example.quanlytaisanbt2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.Data.DataAsset
import com.example.quanlytaisanbt2.Data.DataPerson
import com.example.quanlytaisanbt2.UI.formatMoney
import com.example.quanlytaisanbt2.adapter.AssetAdapter
import com.example.quanlytaisanbt2.adapter.PersonAdapter
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var assetAdapter: AssetAdapter

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(BT2, "onCreate")

        binding.layoutPeople.visibility = View.VISIBLE
        binding.layoutAssets.visibility = View.GONE
        Log.d(BT2, "View mặc định")

        binding.radioGroupPeopleAndAsset.setOnCheckedChangeListener { _, checkid ->
            when (checkid) {
                R.id.radiobuttonPeople -> {
                    binding.layoutPeople.visibility = View.VISIBLE
                    binding.layoutAssets.visibility = View.GONE
                    Log.d(BT2, PERSONVIEW)

                }
                R.id.radiobuttonAsset -> {
                    binding.layoutPeople.visibility = View.GONE
                    binding.layoutAssets.visibility = View.VISIBLE
                    Log.d(BT2, ASSETVIEW)

                }
            }
        }

        val peopleList = listOf(
            DataPerson("Nguyễn Văn Trung", listOf("Xe ô tô", "Xê ô tô", "Điện thoại")),
            DataPerson("Nguyễn Văn Cường", listOf("Xe ô tô", "Điện thoại"))
        )

        val assetList = listOf(
            DataAsset("Xe ô tô", 300_000_000),
            DataAsset("Xe máy", 100_000_000),
            DataAsset("Điện thoại", 50_000_000)
        )


        val peopleRecyclerView = findViewById<RecyclerView>(R.id.peopleRecyclerView)
        peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(peopleList)
        peopleRecyclerView.adapter = personAdapter

        val assetsRecyclerView = findViewById<RecyclerView>(R.id.assetsRecyclerView)
        assetsRecyclerView.layoutManager = LinearLayoutManager(this)
        assetAdapter = AssetAdapter(assetList)
        assetsRecyclerView.adapter = assetAdapter


        binding.personResult.setOnClickListener {
            val intentMain: Intent = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

        binding.assetResults.setOnClickListener {
            val intentMain: Intent = Intent(this@MainActivity, ResultsScreen::class.java)
            startActivity(intentMain)
            Log.d(BT2, "Chuyển sang màn hình kết quả")
        }

    }
    companion object {
        private const val BT2 = "baitap2"
        private const val PERSONVIEW = "View Con người"
        private const val ASSETVIEW = "View Tài sản"
    }
}
