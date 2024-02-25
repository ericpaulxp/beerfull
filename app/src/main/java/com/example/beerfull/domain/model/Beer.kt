package com.example.beerfull.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Beer(
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Double,
    val first_brewed: String,
    val ibu: Double,
    @PrimaryKey
    val id: Int,
    var image_url: String?,
    val name: String,
    val ph: Double,
    val tagline: String,
) : Parcelable

