package com.turtlemint.assignment.datasource.local.database.dao

import androidx.room.*
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity

@Dao
interface IssueDao {
    @Query("SELECT * FROM issueentity")
    suspend fun getAll(): List<IssueEntity>

    @Insert
    suspend fun insert(user: IssueEntity)

    @Update
    suspend fun update(user: IssueEntity)

    @Delete
    suspend fun delete(user: IssueEntity)

    @Query("DELETE FROM issueentity")
    suspend fun deleteAll()
}
