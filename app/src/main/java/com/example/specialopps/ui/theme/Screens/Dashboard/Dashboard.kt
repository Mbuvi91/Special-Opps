package com.example.specialopps.ui.theme.Screens.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.specialopps.Data.AuthViewModel
import com.example.specialopps.navigation.ROUTE_ADD_STUDENT
import com.example.specialopps.navigation.ROUTE_HOME
import com.example.specialopps.navigation.ROUTE_VIEW_STUDENT
import com.example.specialopps.navigation.ROUTE_LOGIN
import com.example.specialopps.navigation.ROUTE_NOTIFICATION
import com.example.specialopps.navigation.ROUTE_SETTINGS

@Composable
fun DashboardScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color.Blue, Color.Green, Color.Black)))
    ) {
        DashboardHeader(navController)
        Row(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
            DashboardSidebar(navController)
            MainDashboardContent()
        }
        DashboardFooter()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardHeader(navController: NavController) {
    TopAppBar(
        title = { Text("Dashboard", color = Color.White) },
        actions = {
            IconButton(onClick = { navController.navigate(ROUTE_NOTIFICATION) }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun DashboardSidebar(navController: NavController) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(Brush.linearGradient(listOf(Color.Blue, Color.Green, Color.Black)))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Dashboard", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White))
        Spacer(modifier = Modifier.height(30.dp))
        SidebarItem(icon = Icons.Filled.Home, text = "Home", onClick = { navController.navigate(ROUTE_HOME) })
        SidebarItem(icon = Icons.Filled.Person, text = "Users", onClick = { navController.navigate(ROUTE_VIEW_STUDENT) })
        SidebarItem(icon = Icons.Filled.Settings, text = "Settings", onClick = { navController.navigate(ROUTE_SETTINGS) })
    }
}

@Composable
fun SidebarItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(icon, contentDescription = text, modifier = Modifier.padding(end = 16.dp), tint = Color.Gray)
        Text(text, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.Gray))
    }
}

@Composable
fun MainDashboardContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Total Users", value = "1", backgroundColor = Color.Blue)
            InfoCard(title = "Active Users", value = "50", backgroundColor = Color.Green)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun InfoCard(title: String, value: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(0.45f)
            .padding(16.dp)
    ) {
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 22.sp, color = Color.White)
        }
    }
}

@Composable
fun DashboardFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
           ,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "© 2025 Special Opps. All Rights Reserved.", color = Color.White, style = TextStyle(fontSize = 14.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}
