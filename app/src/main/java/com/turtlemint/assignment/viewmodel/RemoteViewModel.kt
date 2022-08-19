package com.turtlemint.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.assignment.datasource.Resource
import com.turtlemint.assignment.datasource.local.LocalDataSource
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.datasource.remote.RemoteDataSource
import com.turtlemint.assignment.datasource.remote.ResourceError
import com.turtlemint.assignment.datasource.remote.RetrofitFactory
import com.turtlemint.assignment.datasource.remote.model.ProgressDownloadModel
import com.turtlemint.assignment.datasource.remote.model.ResourceDownloadedModel
import com.turtlemint.assignment.utils.Utils
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

    fun getAllIssues(){

        viewModelScope.launch {
            try {
                val response = remoteDataSource.getIssues()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    for (item in response.body()!!) {
                        val issue = IssueEntity(item.title, item.number)
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
