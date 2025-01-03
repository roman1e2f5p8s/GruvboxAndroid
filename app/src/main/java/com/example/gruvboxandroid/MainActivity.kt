package com.example.gruvboxandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gruvboxandroid.ui.mainContent

class MainActivity : ComponentActivity() {
    private var _darkMode: Boolean? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("DarkMode", _darkMode!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (savedInstanceState != null) {
                _darkMode = savedInstanceState.getBoolean("DarkMode")

            }
            _darkMode = mainContent(_darkMode)
        }
    }
}