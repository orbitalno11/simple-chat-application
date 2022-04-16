package com.example.simplechatapp.model.network

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T>(
    @SerializedName("data")
    var data: T
)