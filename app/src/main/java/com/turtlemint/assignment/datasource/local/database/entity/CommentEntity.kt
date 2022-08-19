package com.turtlemint.assignment.datasource.local.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CommentEntity(
    @ColumnInfo(name = "comment_id") val comment_id: String = "",
    @ColumnInfo(name = "updated_at") val updated_at: String = "",
    @ColumnInfo(name = "body") val body: String = "",
    @ColumnInfo(name = "user") val user: String = "",
    @ColumnInfo(name = "avatar") val avatar: String = "",

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}