package com.example.specialopps.ui.theme.Screens.Home

import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.specialopps.Data.AuthViewModel
import com.example.specialopps.navigation.ROUTE_ADD_STUDENT
import com.example.specialopps.navigation.ROUTE_DASHBOARD
import com.example.specialopps.navigation.ROUTE_PROFILE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val mAuth: AuthViewModel = viewModel()
    val selectedItem = remember { mutableStateOf(0) }
    val context = LocalContext.current
    var showPasswordDialog by remember { mutableStateOf(false) }


    val infiniteTransition = rememberInfiniteTransition()

    val color1 by infiniteTransition.animateColor(
        initialValue = Color(0xFF2196F3),
        targetValue = Color(0xFF4CAF50),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color2 by infiniteTransition.animateColor(
        initialValue = Color(0xFF4CAF50),
        targetValue = Color(0xFF81D4FA),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedBrush = Brush.verticalGradient(
        colors = listOf(color1, color2)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = animatedBrush)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Home", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    selectedItem = selectedItem.value,
                    onItemSelected = { index -> selectedItem.value = index },
                    navController = navController
                )
            },
            containerColor = Color.Transparent,
            contentColor = Color.White,
            modifier = Modifier.background(brush = animatedBrush)
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Welcome !",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    FeatureCard("Student Log in", Icons.Default.AccountBox) {
                        navController.navigate(ROUTE_ADD_STUDENT)
                    }
                }

                item {
                    FeatureCard("Dashboard", Icons.Default.Home) {
                        showPasswordDialog = true
                    }
                }

                item {
                    FeatureCard("Scan Barcode", Icons.Default.Lock) {
                        Toast.makeText(context, "Scan feature coming soon!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        if (showPasswordDialog) {
            PasswordDialog(
                onPasswordCorrect = {
                    navController.navigate(ROUTE_DASHBOARD)
                    showPasswordDialog = false
                },
                onDismiss = { showPasswordDialog = false }
            )
        }
    }
}

@Composable
fun FeatureCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF8BC34A),
        targetValue = Color(0xFFCDDC39),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawRoundRect(
                    color = glowColor.copy(alpha = 0.6f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(20.dp.toPx(), 20.dp.toPx()),
                    size = size
                )
            }
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Transparent)
            .clickable { onClick() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xAA000000)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    navController: NavController
) {
    val items = listOf("Home", "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.AccountBox)
    val routes = listOf(ROUTE_ADD_STUDENT, ROUTE_PROFILE)

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF2196F3), Color(0xFF4CAF50))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(brush = gradientBrush)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = {
                        onItemSelected(index)
                        navController.navigate(routes[index])
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Composable
fun PasswordDialog(
    onPasswordCorrect: () -> Unit,
    onDismiss: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Password") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    },
                    singleLine = true,
                    isError = errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (password == "1237") {
                        onPasswordCorrect()
                    } else {
                        errorMessage = "Incorrect password. Please try again."
                    }
                }
            ) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
