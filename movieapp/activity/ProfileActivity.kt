package com.example.movieapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.BottomNavigationBar
import com.example.movieapp.R
import com.example.movieapp.ui.theme.MovieAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class ProfileActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setContent {
            MovieAppTheme {
                ProfileScreen(
                    auth.currentUser,
                    db
                )
            }
        }
    }

}
@Composable
fun ProfileScreen(user: FirebaseUser?, db: FirebaseFirestore) {
    val context = LocalContext.current
    var showMessage by remember { mutableStateOf<String?>(null) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var selectedAvatar by remember { mutableStateOf(R.drawable.profile) }

    // ✅ This loads the user profile when UID is available
    LaunchedEffect(user?.uid) {
        user?.let {
            db.collection("users").document(it.uid).get()
                .addOnSuccessListener { document ->
                    name = document.getString("name") ?: ""
                    email = document.getString("email") ?: ""
                    phone = document.getString("phone") ?: ""
                    bio = document.getString("bio") ?: ""
                    selectedAvatar = document.getLong("avatar")?.toInt() ?: R.drawable.profile
                }
        }
    }

    // ✅ Only show toast here, don't try to access UID from a String
    LaunchedEffect(showMessage) {
        showMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            showMessage = null
        }
    }

    // UI remains same...
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = selectedAvatar),
                    contentDescription = "Selected Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
                    listOf(R.drawable.avatar1, R.drawable.avatar2).forEach { avatarRes ->
                        Image(
                            painter = painterResource(id = avatarRes),
                            contentDescription = "Avatar Option",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .clickable {
                                    selectedAvatar = avatarRes
                                }
                                .padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(name, fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                Text("Email: $email", color = Color.White, fontSize = 16.sp)
                EditableField("Phone", phone) { phone = it }
                EditableField("Bio", bio) { bio = it }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (!phone.matches(Regex("^\\d{10}\$"))) {
                            Toast.makeText(context, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
                        } else {
                            val updatedData = mapOf(
                                "name" to name,
                                "email" to email,
                                "phone" to phone,
                                "bio" to bio,
                                "avatar" to selectedAvatar
                            )
                            user?.let {
                                db.collection("users").document(it.uid).set(updatedData)
                                    .addOnSuccessListener {
                                        showMessage = "Profile updated successfully"
                                    }
                                    .addOnFailureListener {
                                        showMessage = "Failed to update profile"
                                    }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Save Profile", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        context.startActivity(Intent(context, SplashActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        }
    }
}



@Composable
fun EditableField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        textStyle = LocalTextStyle.current.copy(color = Color.White)
    )
}

