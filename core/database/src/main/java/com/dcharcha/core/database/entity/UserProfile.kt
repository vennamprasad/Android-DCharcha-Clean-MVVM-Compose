package com.dcharcha.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val photoUri: String?
)