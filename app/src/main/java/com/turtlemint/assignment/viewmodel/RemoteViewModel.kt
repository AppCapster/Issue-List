package com.turtlemint.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.assignment.datasource.Resource
import com.turtlemint.assignment.datasource.local.LocalDataSource
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.datasource.remote.RemoteDataSource
import com.turtlemint.assignment.datasource.remote.ResourceError
import com.turtlemint.assignment.datasource.remote.model.ResourceDownloadedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RemoteViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val uriLiveData: MutableLiveData<Resource<ResourceDownloadedModel, ResourceError>> =
        MutableLiveData()

    fun getAllIssues() {

        viewModelScope.launch {
            try {
                val response = remoteDataSource.getIssues()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    for (item in response.body()!!) {
                        var labels = ""
                        var labelsColor = ""
                        if (!item.labels.isNullOrEmpty()) {
                            labels = item.labels!![0].name.toString()
                            labelsColor = item.labels!![0].color.toString()
                        }
                        val issue = IssueEntity(
                            item.title,
                            item.number,
                            item.updated_at,
                            item.body,
                            item.user.login,
                            item.user.avatar_url,
                            labels, labelsColor
                        )
                        localDataSource.insertIssue(issue)
                    }
                    progressLiveData.postValue(true)
                } else {
                    progressLiveData.postValue(false)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.printStackTrace()
                    progressLiveData.postValue(false)
                }
            }
        }
    }
}
