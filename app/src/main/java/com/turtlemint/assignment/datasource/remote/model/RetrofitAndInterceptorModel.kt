package com.turtlemint.assignment.datasource.remote.model

import com.turtlemint.assignment.datasource.remote.interceptor.DownloadProgressInterceptor
import retrofit2.Retrofit


data class RetrofitAndInterceptorModel(
    val retrofit: Retrofit,
    val downloadProgressInterceptor: DownloadProgressInterceptor
)
