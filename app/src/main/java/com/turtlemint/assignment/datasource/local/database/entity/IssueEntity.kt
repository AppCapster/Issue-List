package com.turtlemint.assignment.datasource.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turtlemint.assignment.datasource.remote.response.Labels
import com.turtlemint.assignment.datasource.remote.response.User

@Entity
data class IssueEntity(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "number") val number: String?,
    @ColumnInfo(name = "updated_at") val updated_at: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "label") var label:  String?,
    @ColumnInfo(name = "label_color") var label_color:  String?
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
