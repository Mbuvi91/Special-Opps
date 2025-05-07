package com.example.specialopss.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.need.ui.theme.screens.students.UpdatestudentScreen
import com.example.specialopps.ui.theme.Screens.students.AddstudentScreen
import com.example.specialopps.ui.theme.Screens.students.UpdatestudentScreen
import com.example.specialopps.ui.theme.Screens.students.ViewStudents
import com.example.specialopps.navigation.ROUTE_REGISTER
import com.example.specialopps.navigation.ROUTE_UPDATE_STUDENT
import com.example.specialopps.ui.theme.Screens.Register.RegisterScreen
import com.example.specialopps.ui.theme.Screens.login.LoginScreen


@Composable
fun AppNavHost(navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_REGISTER){
    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_REGISTER) { RegisterScreen{navController.navigate(ROUTE_REGISTER){popUpTo(
            ROUTE_REGISTER){inclusive=true}} }
        }

        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_HOME) { (navController) }
        composable(ROUTE_ADD_STUDENT) { AddstudentScreen(navController) }
        composable(ROUTE_VIEW_STUDENT){ ViewStudents(navController) }
        composable("$ROUTE_UPDATE_STUDENT/{productId}") {
                passedData -> UpdatestudentScreen(
            navController, passedData.arguments?.getString("productId")!! )


        }
    }

}



