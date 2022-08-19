package com.turtlemint.assignment.datasource.remote

import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.datasource.remote.model.RetrofitAndInterceptorModel
import com.turtlemint.assignment.datasource.remote.response.CommentsResponse
import com.turtlemint.assignment.datasource.remote.response.IssueResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Streaming
import retrofit2.http.Url


class RemoteDataSource {

    private val retrofitUnAuth: Retrofit =
        RetrofitFactory.getUnAuthRetrofit()

    private val retrofitAuth: Retrofit =
        RetrofitFactory.getAuthRetrofit()

    suspend fun getIssues(): Response<List<IssueResponse>> {
        return retrofitAuth.create(IssuesClient::class.java).getIssues()
    }
    suspend fun getComments(url: String): Response<List<CommentsResponse>> {
        return retrofitAuth.create(IssuesClient::class.java).getComments(url)
    }

    suspend fun getResource(
        url: String,
        retrofitAndInterceptorModel: RetrofitAndInterceptorModel
    ): Response<ResponseBody> {
        return retrofitAndInterceptorModel.retrofit.create(DownloadClient::class.java)
            .getResource(url)
    }

}

enum class ResourceError { UNKNOWN, UNAUTHENTICATED, NETWORK }

interface IssuesClient {

    @GET("square/okhttp/issues")
    suspend fun getIssues(): Response<List<IssueResponse>>

    @GET
    suspend fun getComments(@Url urlDownload: String?): Response<List<CommentsResponse>>
}

interface DownloadClient {

    @Streaming
    @Headers("Content-Type:application/zip")
    @GET
    suspend fun getResource(@Url urlDownload: String?): Response<ResponseBody>

}
