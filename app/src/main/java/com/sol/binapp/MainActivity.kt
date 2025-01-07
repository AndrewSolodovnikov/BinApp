package com.sol.binapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sol.binapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.shadows)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    navController.navigate(R.id.binFinderFragment, null, navOptions)
                    true
                }

                R.id.history -> {
                    navController.navigate(R.id.binHistoryFragment, null, navOptions)
                    true
                }

                else -> false
            }
        }
    }
}

fun createOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}
