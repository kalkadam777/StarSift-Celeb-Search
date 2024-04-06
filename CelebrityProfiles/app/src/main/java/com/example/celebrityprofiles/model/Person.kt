package com.example.celebrityprofiles.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Person(
    val name: String,
    @SerializedName("net_worth") val netWorth: Long,
    val gender: String,
    val nationality: String?=null,
    val occupation: List<String>?=null,
    val height: Double,
    val birthday: String?=null,
    val age: Int?=null
)
