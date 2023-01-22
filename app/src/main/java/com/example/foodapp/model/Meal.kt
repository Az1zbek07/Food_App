package com.example.foodapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: ByteArray,
    val name: String,
    val rating: Double,
    val ingredients: String,
    val directions: String
): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Meal

        if (id != other.id) return false
        if (!image.contentEquals(other.image)) return false
        if (name != other.name) return false
        if (rating != other.rating) return false
        if (ingredients != other.ingredients) return false
        if (directions != other.directions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + image.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + ingredients.hashCode()
        result = 31 * result + directions.hashCode()
        return result
    }
}
