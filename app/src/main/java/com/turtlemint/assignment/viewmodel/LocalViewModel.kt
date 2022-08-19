package com.turtlemint.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.assignment.datasource.Resource
import com.turtlemint.assignment.datasource.local.LocalDataSource
import com.turtlemint.assignment.datasource.local.PersistanceError
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import kotlinx.coroutines.launch

class LocalViewModel(private val localDataSource: LocalDataSource) : ViewModel() {

    fun getIssues(): LiveData<Resource<List<IssueEntity>?, PersistanceError>> {
        val data = MutableLiveData<Resource<List<IssueEntity>?, PersistanceError>>()
        viewModelScope.launch {
            try {
                val issues = localDataSource.getIssues()
                data.postValue(Resource.success(issues))
            } catch (e: Exception) {
                data.postValue(
                    Resource.error(
                        PersistanceError.UNKNOWN, null
                    )
                )
            }
        }
        return data
    }

    fun createIssue(
        firstName: String,
        lastName: String
    ): LiveData<Resource<Boolean, PersistanceError>> {
        val data = MutableLiveData<Resource<Boolean, PersistanceError>>()
        viewModelScope.launch {
            try {
                localDataSource.insertIssue(IssueEntity(firstName, lastName))
                data.postValue(Resource.success(true))
            } catch (e: Exception) {
                data.postValue(Resource.error(PersistanceError.UNKNOWN, e.message))
            }
        }
        return data
    }

    fun deleteIssue(issue: IssueEntity): LiveData<Resource<Boolean, PersistanceError>> {
        val data = MutableLiveData<Resource<Boolean, PersistanceError>>()
        viewModelScope.launch {
            try {
                localDataSource.deleteIssue(issue)
                data.postValue(Resource.success(true))
            } catch (e: Exception) {
                data.postValue(Resource.error(PersistanceError.UNKNOWN, e.message))
            }
        }
        return data
    }

}
