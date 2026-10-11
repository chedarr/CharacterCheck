package com.example.charactercheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercheck.ui.theme.CharacterCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCheckTheme {
                CharacterCheck()
            }
        }
    }
}



@Composable
fun CharacterCheck() {
    var symbol by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Проверка символа",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Введите один символ") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = if (symbol.length != 1) {
                    "Введите ровно один символ"
                } else {
                    when (symbol[0]){
                        in 'a'..'z' -> when(symbol[0]){
                            'a','e','i','o','u' -> "Это гласные буквы"
                            else -> "Возможно, это согласные буквы"
                        }
                        else -> "Ошибка! Введите строчную латинскую букву"
                    }
                }
            }
        ) {
            Text("Проверить")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = result,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CharacterCheckPreview() {
    CharacterCheckTheme {
        CharacterCheck()
    }
}