package com.example.quanlytaisanbt2.layout_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlytaisanbt2.category.CategoryAdapter
import com.example.quanlytaisanbt2.category.DataCategory
import com.example.quanlytaisanbt2.databinding.ActivityRecyclerViewProBinding
import com.example.quanlytaisanbt2.movie.DataMovie
import com.example.quanlytaisanbt2.search.SearchHandler

class RecyclerViewPro : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewProBinding
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataMovie>
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var searchHandler: SearchHandler
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewProBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartoonMovies = listOf(
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219103835-637177055151245839.jpg", "Zootopia"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219103915-637177055554551480.jpg", "Kung Fu Panda 3"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219104023-637177056237162869.jfif", "Toy Story 3"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219113516-637177089165069637.jpg", "Incredibles 2"),
            DataMovie("https://homepage.momocdn.net/blogscontents/momo-upload-api-200219113751-637177090711186072.jfif", "Big Hero 6")
        )

        val actionMovies = listOf(
            DataMovie("https://miro.medium.com/v2/resize:fit:1400/0*NqKF3_Hg3XFmBf9r.jpg", "Bad Boys:Ride or Die"),
            DataMovie("https://kollersi.com/wp-content/uploads/2024/05/mv5by2qwoge2ngqtmwqwni00m2izlthlnwitywmzngq5ywnizda4xkeyxkfqcgdeqxvynte1njy5mg-_v1_fmjpg_ux1000_.jpg", "Godzilla x Kong"),
            DataMovie("https://ew.com/thmb/wEBdITM-T72RdK1cP3F0CbUH7ls=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Baby-Driver-ANSEL-ELGORT-020624-24ccfd751355451aadc7616f0fe67c6e.jpg", "Baby Driver (2017)"),
            DataMovie("https://ew.com/thmb/6ujx6w5xFzsySH4lJgwZ8zIc9bA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/HP_03077-2000-38090f572e5442859a878d7ce8e06afd.jpg", "Cold Pursuit (2019)"),
            DataMovie("https://static1.moviewebimages.com/wordpress/wp-content/uploads/2023/12/role_play_xlg.jpg?q=50&fit=crop&w=480&dpr=1.5", "Role Play")
        )

        val dramaMovies = listOf(
            DataMovie("https://resizing.flixster.com/vHBBVpu_qNHHuuyTHD_MdOSIq3M=/206x305/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p27924961_v_v13_ac.jpg", "Long Gone Heroes"),
            DataMovie("https://resizing.flixster.com/lZAfN-e7fCd4fJA-EpycHhCMU9o=/206x305/v2/https://resizing.flixster.com/obj-joCBFf7JlX5grM2pC1xMO_4=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzlhNzFhNWViLThiNjEtNGJkMi1iZDllLWE1Y2ZmZTc1NWU2My5qcGc=", "Eureka"),
            DataMovie("https://resizing.flixster.com/-R0QNXiAqm69eL_U7ZpkBxSW5zA=/206x305/v2/https://resizing.flixster.com/2MI8OOyx5tlsMyFlt89rVOeutOI=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2Y2ZjJiMGM3LWRiYjQtNGI4Ni04NDA2LTg0M2ZmYWU3OTYzOC5wbmc=", "And Mrs"),
            DataMovie("https://ew.com/thmb/6ujx6w5xFzsySH4lJgwZ8zIc9bA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/HP_03077-2000-38090f572e5442859a878d7ce8e06afd.jpg", "Cold Pursuit (2019)"),
            DataMovie("https://static1.moviewebimages.com/wordpress/wp-content/uploads/2023/12/role_play_xlg.jpg?q=50&fit=crop&w=480&dpr=1.5", "Role Play")
        )

        val animeMovies = listOf(
            DataMovie("https://hoanghamobile.com/tin-tuc/wp-content/webp-express/webp-images/uploads/2024/03/anime-hay-nhat-1.jpg.webp", "One Piece"),
            DataMovie("https://hoanghamobile.com/tin-tuc/wp-content/webp-express/webp-images/uploads/2024/03/anime-hay-nhat-2.jpg.webp", "Naruto"),
            DataMovie("https://hoanghamobile.com/tin-tuc/wp-content/webp-express/webp-images/uploads/2024/03/anime-hay-nhat-4.jpg.webp", "Odd Taxi"),
            DataMovie("https://hoanghamobile.com/tin-tuc/wp-content/webp-express/webp-images/uploads/2024/03/anime-hay-nhat-6.jpg.webp", "Demon Slayer"),
            DataMovie("https://hoanghamobile.com/tin-tuc/wp-content/webp-express/webp-images/uploads/2024/03/anime-hay-nhat-9.jpg.webp", "Doraemon")
        )
        val horrorMovies = listOf(
            DataMovie("https://filmfare.wwmindia.com/content/2024/apr/besthollywoodhorrormoviesthatwillscareyoutothecore11713786375.jpg", "Abigail (2024)"),
            DataMovie("https://filmfare.wwmindia.com/content/2023/jan/besthollywoodhorrormovies1674216335.jpg", "A Quiet Place"),
            DataMovie("https://filmfare.wwmindia.com/content/2023/jan/besthollywoodhorrormovies11674128256.jpg", "Hereditary "),
            DataMovie("https://filmfare.wwmindia.com/content/2021/aug/best-english-horror-movies-the-witch-81629283638.jpg", "The Witch"),
            DataMovie("https://filmfare.wwmindia.com/content/2021/aug/best-english-horror-movies-it-21629283608.jpg", "IT (2017)")
        )

        val categoryList = listOf(
            DataCategory("Action", actionMovies),
            DataCategory("Cartoon", cartoonMovies),
            DataCategory("Drama", dramaMovies ),
            DataCategory("Sci-Fi", animeMovies),
            DataCategory("Horror", horrorMovies)
        )

        // Cài đặt adapter cho RecyclerView dọc
        val categoryAdapter = CategoryAdapter(categoryList)
        binding.RecyclerViewPro.layoutManager = LinearLayoutManager(this)
        binding.RecyclerViewPro.adapter = categoryAdapter

        //chức năng tìm kiếm

        searchHandler = SearchHandler(searchView, categoryList, recyclerView, categoryAdapter)
        searchHandler.setupSearch() // Gọi hàm thiết lập tìm kiếm
    }

}
