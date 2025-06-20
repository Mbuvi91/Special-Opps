package com.example.specialopps.Data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.specialopps.navigation.ROUTE_HOME
import com.example.specialopps.navigation.ROUTE_LOGIN
import com.example.specialopps.Models.UserModel
import com.example.specialopps.navigation.ROUTE_DASHBOARD
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

 class AuthViewModel:ViewModel(){
     private val mAuth:FirebaseAuth=FirebaseAuth.getInstance()
     private val _isLoading = MutableStateFlow(false)
     private val _errorMessage = MutableStateFlow<String?>(null)

     fun signup(firstname:String, email:String, password:String,
                navController: NavController,
                context: Context
     ){
         if (firstname.isBlank()||  email.isBlank() || password.isBlank()){
             Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()

             return
         }
         _isLoading.value=true

         mAuth.createUserWithEmailAndPassword(email,password)
             .addOnCompleteListener { task ->
                 _isLoading.value=false
                 if (task.isSuccessful){
                     val userId = mAuth.currentUser?.uid?:""
                     val userData = UserModel(firstname=firstname,
                         email=email,password=password,userId=userId)
                     saveUserToDatabase(userId,userData,navController,context)

                 }else{
                     _errorMessage.value = task.exception?.message

                     Toast.makeText(context,"Registration failed", Toast.LENGTH_LONG).show()
                 }
             }
     }
     fun saveUserToDatabase(userId:String, userData:UserModel,
                            navController: NavController,
                            context: Context
     ){
         val regRef= FirebaseDatabase.getInstance()
             .getReference("Users/$userId")
         regRef.setValue(userData).addOnCompleteListener { regRef ->
             if (regRef.isSuccessful){

                 Toast.makeText(context,"User Successfully Registered", Toast.LENGTH_LONG).show()
                 navController.navigate(ROUTE_LOGIN)
             }else{
                 _errorMessage.value=regRef.exception?.message
                 Toast.makeText(context,"Database error", Toast.LENGTH_LONG).show()
             }
         }
     }
     fun login(email: String,password: String,navController: NavController,context: Context){
         if (email.isBlank() || password.isBlank()){
             Toast.makeText(context,"Email and password required",Toast.LENGTH_LONG).show()

             return
         }
         _isLoading.value=true

         mAuth.signInWithEmailAndPassword( email,password)
             .addOnCompleteListener { task ->
                 _isLoading.value=false
                 if (task.isSuccessful){
                     Toast.makeText(context,"User successfully logged in",Toast.LENGTH_LONG).show()
                     navController.navigate(ROUTE_HOME)

                 }else{
                     _errorMessage.value = task.exception?.message

                     Toast.makeText(context,"Login failed", Toast.LENGTH_LONG).show()
                 }
             }
     }

 }

