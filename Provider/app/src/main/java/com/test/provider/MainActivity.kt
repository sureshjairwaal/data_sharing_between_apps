package com.test.provider

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.test.provider.db.entities.GENDER
import com.test.provider.db.entities.PersonEntity
import com.test.provider.ui.theme.ProviderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[VMPerson::class.java]
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProviderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var userName by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(value = userName, placeholder = {
                            Text(text = "Username")
                        }, onValueChange = {
                            userName = it
                            Log.d("testing", "Username: $it")
                        })
                        var age by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(value = age, placeholder = {
                            Text(text = "Age")
                        }, onValueChange = {
                            age = it
                            Log.d("testing", "Age: $it")
                        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
                        var gender by remember { mutableStateOf("") }
                        TextField(value = gender, placeholder = {
                            Text(text = "Gender")
                        }, onValueChange = {
                            gender = it
                            Log.d("testing", "Age: $it")
                        })
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            viewModel.insertPeople(PersonEntity(name = userName.text, age = age.text, gender = gender))
                        }) {
                            Text(text = "Click Me")
                        }
                    }
                }
            }
        }
    }
}