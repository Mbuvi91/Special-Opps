package com.example.specialopps.ui.theme.Screens.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.google.android.libraries.intelligence.acceleration.Analytics

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color.Blue, Color.Green))) // Gradient background
            .padding(16.dp)
    ) {
        // Header Section (App Bar)
        DashboardHeader()

        // Layout: Sidebar and Main Content
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            // Sidebar
            DashboardSidebar()

            // Main Content Area
            MainDashboardContent()
        }

        // Footer Section (Optional)
        DashboardFooter()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardHeader() {
    TopAppBar(
        title = { Text("Dashboard", color = Color.White) },
        actions = {
            IconButton(onClick = { /* Handle Notifications */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.White)
            }
            IconButton(onClick = { /* Handle Profile Settings */ }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Profile", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Black)
    )
}

@Composable
fun DashboardSidebar() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Dashboard", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(30.dp))

        SidebarItem(icon = Icons.Filled.Home, text = "Home", onClick = { /* Handle Home click */ })
        SidebarItem(icon = Icons.Filled.Person, text = "Users", onClick = { /* Handle Users click */ })
        SidebarItem(icon = Icons.Filled.Info, text = "Reports", onClick = { /* Handle Reports click */ })
        SidebarItem(icon = Icons.Filled.Settings, text = "Settings", onClick = { /* Handle Settings click */ })
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
        // Key Metrics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Total Users", value = "1,230", backgroundColor = Color.Blue)
            InfoCard(title = "Active Users", value = "980", backgroundColor = Color.Green)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Graphs Section (This is where you would add charts)

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray)
        ) {
            // You can add your graph/chart here (using a library like MPAndroidChart or ComposeCharts)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Recent Activity Feed Section
        Text(
            text = "Recent Activity",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun InfoCard(title: String, value: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(0.28f)
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
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "© 2025 Special Opps. All Rights Reserved.", color = Color.White, style = TextStyle(fontSize = 14.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
