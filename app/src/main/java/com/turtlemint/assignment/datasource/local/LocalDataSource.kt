package com.turtlemint.assignment.datasource.local

import com.turtlemint.assignment.datasource.local.database.AppDatabase
import com.turtlemint.assignment.datasource.local.database.entity.CommentEntity
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

    //for Comments
    suspend fun getComments(): List<CommentEntity> {
        return db.commentDao().getAll()
    }

    suspend fun insertComment(issue: CommentEntity) {
        db.commentDao().insert(issue)
    }

    suspend fun updateComment(issue: CommentEntity) {
        db.commentDao().update(issue)
    }

    suspend fun deleteComment(issue: CommentEntity) {
        db.commentDao().delete(issue)
    }

    suspend fun deleteAllComments() {
        db.commentDao().deleteAll()
    }
}

enum class PersistanceError { UNKNOWN, UNAUTHORIZED }
