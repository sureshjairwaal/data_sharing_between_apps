package com.test.reciever

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.reciever.ui.theme.RecieverTheme

class MainActivity : ComponentActivity() {
    var uri = Uri.parse("content://com.test.provider.myProvider/person_entity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecieverTheme {
                var text by remember { mutableStateOf("Click a button") }
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
                        Greeting(name = text)
                        Button(onClick = {
                            text = getData()
                        }) {
                            Text(text = "Get Data")
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun getData(): String {
        var cursor = contentResolver.query(uri, null, null, null, "id")
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append(
                    """${cursor.getString(cursor.getColumnIndex("id"))}-${
                        cursor.getString(
                            cursor.getColumnIndex("name")
                        )
                    }""".trimIndent()
                )
                cursor.moveToNext()
            }
            Log.d("", "$strBuild")
            return strBuild.toString()
        } else {
            Log.d("", "No Records Found")
        }
        return "nothing"
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecieverTheme {
        Greeting("Android")
    }
}