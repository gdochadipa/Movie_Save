package co.ocha.moviesave.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName="Movies")

@Parcelize
data class Movie (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "description") var description: String?
): Parcelable