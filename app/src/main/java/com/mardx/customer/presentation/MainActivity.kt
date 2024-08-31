package com.mardx.customer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mardx.customer.presentation.ui.screen.BuildProductListScreen
import com.mardx.customer.presentation.ui.theme.MardxCustomerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuildUIContent()
        }
    }

    @Composable
    private fun BuildUIContent() {
        MardxCustomerAppTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                BuildProductListScreen(
                    innerPadding = innerPadding,
                )
            }
        }
    }
}






