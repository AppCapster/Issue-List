package com.turtlemint.assignment.ui.listener

import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity

interface IssueRecyclerListener {
    fun clickIssue(issue: IssueEntity)
}