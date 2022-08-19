package com.turtlemint.assignment.datasource.local

import com.turtlemint.assignment.datasource.local.database.AppDatabase
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity

class LocalDataSource(private val db: AppDatabase) {

    suspend fun getIssues(): List<IssueEntity> {
        return db.issueDao().getAll()
    }

    suspend fun insertIssue(issue: IssueEntity) {
        db.issueDao().insert(issue)
    }

    suspend fun updateIssue(issue: IssueEntity) {
        db.issueDao().update(issue)
    }

    suspend fun deleteIssue(issue: IssueEntity) {
        db.issueDao().delete(issue)
    }

    suspend fun deleteAllIssues() {
        db.issueDao().deleteAll()
    }

}

enum class PersistanceError { UNKNOWN, UNAUTHORIZED }
