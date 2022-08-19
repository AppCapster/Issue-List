package com.turtlemint.assignment.datasource.local.database.dao

import androidx.room.*
import com.turtlemint.assignment.datasource.local.database.entity.CommentEntity

@Dao
interface CommentDao {
    @Query("SELECT * FROM commententity")
    suspend fun getAll(): List<CommentEntity>

    @Insert
    suspend fun insert(user: CommentEntity)

    @Update
    suspend fun update(user: CommentEntity)

    @Delete
    suspend fun delete(user: CommentEntity)

    @Query("DELETE FROM commententity")
    suspend fun deleteAll()
}