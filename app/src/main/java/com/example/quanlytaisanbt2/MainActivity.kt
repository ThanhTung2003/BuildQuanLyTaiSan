package com.example.quanlytaisanbt2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quanlytaisanbt2.adapter.AssetAdapter
import com.example.quanlytaisanbt2.adapter.PersonAdapter
import com.example.quanlytaisanbt2.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var assetAdapter: AssetAdapter
    private lateinit var personAdapter: PersonAdapter
    private var assets: List<Asset> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutPeople.visibility = View.VISIBLE
        binding.layoutAssets.visibility = View.GONE

        setupRecyclerViews()
        fetchAssets()
        fetchPersons()

        binding.radioGroupPeopleAndAsset.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radiobuttonPeople -> {
                    binding.layoutPeople.visibility = View.VISIBLE
                    binding.layoutAssets.visibility = View.GONE
                }
                R.id.radiobuttonAsset -> {
                    binding.layoutPeople.visibility = View.GONE
                    binding.layoutAssets.visibility = View.VISIBLE
                }
            }
        }
        //xem ket qua
        binding.personResult.setOnClickListener {
            navigateToResultsScreen()
        }

        binding.assetResults.setOnClickListener {
            navigateToResultsScreen()
        }
    }

    private var persons: List<Person> = listOf()

    private fun setupRecyclerViews() {
        // Setup LayoutManagers for RecyclerViews
        binding.assetsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.peopleRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchAssets() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://testapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.getAssets().enqueue(object : Callback<AssetResponse> {
            override fun onResponse(call: Call<AssetResponse>, response: Response<AssetResponse>) {
                if (response.isSuccessful) {
                    assets = response.body()?.data ?: emptyList()
                    assetAdapter = AssetAdapter(assets)
                    binding.assetsRecyclerView.adapter = assetAdapter
                    fetchPersons()  //gọi fetchPersons sau khi assets đã sẵn sàng
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load assets", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
                showError("Error: ${t.message}")
            }
        })
    }
    
    private fun fetchPersons() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://testapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.getPersons().enqueue(object : Callback<PersonResponse> {
            override fun onResponse(call: Call<PersonResponse>, response: Response<PersonResponse>) =
                if (response.isSuccessful) {
                    persons = response.body()?.data ?: emptyList()
                    personAdapter = PersonAdapter(persons, assets,false)
                    binding.peopleRecyclerView.adapter = personAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load persons", Toast.LENGTH_SHORT).show()
                }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                showError("Error: ${t.message}")
            }
        })
    }

    private fun navigateToResultsScreen() {
        val taxThreshold = 1_000_000_000
        val taxPayers = persons.filter { it.totalAssetValue(assets) >= taxThreshold }
        val nonTaxPayers = persons.filter { it.totalAssetValue(assets) < taxThreshold }

        val intent = Intent(this@MainActivity, ResultsScreen::class.java).apply {
            putExtra("totalPeople", persons.size)
            putExtra("taxPayers", Gson().toJson(taxPayers))
            putExtra("nonTaxPayers", Gson().toJson(nonTaxPayers))
            putExtra("assets", Gson().toJson(assets))
        }
        startActivity(intent)
    }

    private fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }


}
