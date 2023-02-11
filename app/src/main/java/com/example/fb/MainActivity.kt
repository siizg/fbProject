package com.example.fb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fb.ui.theme.FbTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FbTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    lateinit var auth: FirebaseAuth
                    auth = Firebase.auth

                    var email = remember() {
                        mutableStateOf("")
                    }
                    var password = remember() {
                        mutableStateOf("")
                    }

                    fun createAccount(email : String, password : String ) {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    //updateUI(auth.currentUser)
                                } else {
                                    println("lkljshgfdgrftyghokl;")
                                    println(email)
                                    //updateUI(null)
                                }
                            }

                    }

                    Column(modifier = Modifier.fillMaxSize()) {
                        TextField(email.value, { email.value = it })
                        Spacer(modifier = Modifier.size(4.dp))

                        TextField(password.value, { password.value = it })
                        Spacer(modifier = Modifier.size(4.dp))

                        Button(onClick = { createAccount(email.value.toString(), password.value.toString()) }) {
                        }
                    }
                }
            }
        }
    }
}


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")

    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        FbTheme {
            Greeting("Android")
        }
    }