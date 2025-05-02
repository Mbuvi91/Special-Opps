package com.example.specialopps.ui.theme.Screens.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(){
    var firstname by remember { mutableStateOf(value = "") }
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    var confirmPassword by remember { mutableStateOf(value = "") }
    val context = LocalContext.current
    val passwordVisible by remember { mutableStateOf(false) }

    // Define a gradient for the background
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Blue, Color.Green) // Customize these colors as you wish
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(gradientBrush) // Apply the gradient as the background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Sign Up",
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.White, // White text color for contrast on the gradient background
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Sign up to your account",
            fontSize = 16.sp,
            color = Color.White, // White text color for contrast
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = firstname,
            onValueChange = { firstname = it },
            label = { Text(text = "Full Name") },
            placeholder = { Text(text = "Enter full name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email Address") },
            placeholder = { Text(text = "Enter email address") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Confirm password") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") }
        )

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Sign Up", color = Color.White)
        }

        Text(
            text = buildAnnotatedString {
                append("Already have an account? ")
                append("Sign In")
            },
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    // Add navigation to Sign In screen
                }
                .padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
