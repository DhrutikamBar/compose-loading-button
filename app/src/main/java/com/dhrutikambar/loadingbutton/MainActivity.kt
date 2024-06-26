package com.dhrutikambar.loadingbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhrutikambar.loadingbutton.ui.theme.LoadingButtonTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoadingButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    // Define a flag to remember state of loading
    val isLoading = remember {
        mutableStateOf(false)
    }

    // Launched effect to observe state of loading variable
    LaunchedEffect(key1 = isLoading.value) {
        if (isLoading.value) {
            delay(2000)
            isLoading.value = false
        }
    }

    // Finally the Loading button component
    LoadingButton(isLoading)
}

@Composable
fun LoadingButton(loading: MutableState<Boolean>) {
    // Setting a box takes max size of the screen
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // when a state of loading variable is true, We will show a button with circular progress indicator inside it
        if (loading.value) {
            Button(
                onClick = { },
                enabled = false,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(left = 20.dp, right = 20.dp)
            ) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }

        } else {
            // when a state of loading variable is false, We will show a button with a text inside it
            Button(
                onClick = { loading.value = true }, modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(left = 20.dp, right = 20.dp)
            ) {
                Text(text = "Click Here")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoadingButtonTheme {
        MainScreen("Android")
    }
}