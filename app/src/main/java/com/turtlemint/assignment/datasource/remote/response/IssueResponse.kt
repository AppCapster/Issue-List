package com.turtlemint.assignment.datasource.remote.response

data class IssueResponse(
    var number: String? = null,
    var title: String? = null,
    var user: User,
    var labels: List<Labels>? = null,
    var updated_at: String? = null,
    var body: String? = null,
    var comments_url: String? = null,
    var state: String? = null
)

data class User(
    var login: String? = null,
    var id: String? = null,
    var avatar_url: String? = null,
    var type: String? = null
)

data class Labels(
    var name: String? = null,
    var id: String? = null,
    var color: String? = null
)