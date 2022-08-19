package com.turtlemint.assignment.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.turtlemint.assignment.datasource.local.database.dao.CommentDao
import com.turtlemint.assignment.datasource.local.database.dao.IssueDao
import com.turtlemint.assignment.datasource.local.database.entity.CommentEntity
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity

@Database(entities = [IssueEntity::class, CommentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun issueDao(): IssueDao
    abstract fun commentDao(): CommentDao
}
