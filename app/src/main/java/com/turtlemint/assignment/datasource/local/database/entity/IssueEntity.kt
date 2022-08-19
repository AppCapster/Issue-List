package com.turtlemint.assignment.datasource.local.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class IssueEntity(
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "number") val number: String = "",
    @ColumnInfo(name = "updated_at") val updated_at: String = "",
    @ColumnInfo(name = "body") val body: String = "",
    @ColumnInfo(name = "user") val user: String = "",
    @ColumnInfo(name = "avatar") val avatar: String = "",
    @ColumnInfo(name = "label") var label: String = "",
    @ColumnInfo(name = "label_color") var label_color: String = "",
    @ColumnInfo(name = "comments_url") var comments_url: String = ""

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
