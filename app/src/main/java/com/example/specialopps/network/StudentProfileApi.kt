package com.example.specialopps.Network

import com.example.specialopps.Models.StudentProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StudentProfileApi {
    @POST("student/profile")
    suspend fun saveStudentProfile(
        @Body profile: StudentProfile
    ): Response<Void>
}
