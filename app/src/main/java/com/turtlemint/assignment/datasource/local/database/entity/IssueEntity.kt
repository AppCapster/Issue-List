package com.turtlemint.assignment.datasource.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IssueEntity(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "number") val number: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
