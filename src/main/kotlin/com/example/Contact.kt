package com.example;

import kotlinx.serialization.Serializable

@Serializable
data class Contact (var id: Int, var name: String, var status: String, var image: String)
