package com.example.specialopss.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.need.ui.theme.screens.students.AddstudentScreen
import com.example.need.ui.theme.screens.students.UpdatestudentScreen
import com.example.need.ui.theme.screens.students.ViewStudents
import com.example.specialopps.navigation.ROUTE_ADD_STUDENT
import com.example.specialopps.navigation.ROUTE_DASHBOARD
import com.example.specialopps.navigation.ROUTE_HOME
import com.example.specialopps.navigation.ROUTE_LOGIN
import com.example.specialopps.navigation.ROUTE_NOTIFICATION
import com.example.specialopps.navigation.ROUTE_PROFILE
import com.example.specialopps.navigation.ROUTE_REGISTER
import com.example.specialopps.navigation.ROUTE_SETTINGS
import com.example.specialopps.navigation.ROUTE_UPDATE_STUDENT
import com.example.specialopps.navigation.ROUTE_VIEW_STUDENT
import com.example.specialopps.ui.theme.Screens.Dashboard.DashboardScreen
import com.example.specialopps.ui.theme.Screens.Home.HomeScreen
import com.example.specialopps.ui.theme.Screens.Notification.NotificationScreen
import com.example.specialopps.ui.theme.Screens.Register.RegisterScreen
import com.example.specialopps.ui.theme.Screens.Settings.SettingsScreen
import com.example.specialopps.ui.theme.Screens.login.LoginScreen
import com.example.specialopps.ui.theme.Screens.students.StudentProfileScreen


@Composable
fun AppNavHost(navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_REGISTER){
    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_REGISTER) {
        }

        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_DASHBOARD){ DashboardScreen(navController) }
        composable (ROUTE_SETTINGS ) { SettingsScreen(navController) }
        composable  (ROUTE_NOTIFICATION){ NotificationScreen(navController) }
        composable (ROUTE_PROFILE){ StudentProfileScreen(navController) }
        composable(ROUTE_HOME){ HomeScreen(navController) }
        composable(ROUTE_ADD_STUDENT) { AddstudentScreen(navController) }
        composable(ROUTE_VIEW_STUDENT){ ViewStudents(navController) }
        composable("$ROUTE_UPDATE_STUDENT/{productId}") {
                passedData -> UpdatestudentScreen(
            navController, passedData.arguments?.getString("productId")!! )


        }
    }

}



