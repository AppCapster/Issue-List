package com.turtlemint.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.turtlemint.assignment.databinding.ActivityMainBinding
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.ui.adapter.IssueRecyclerAdapter
import com.turtlemint.assignment.ui.listener.IssueRecyclerListener
import com.turtlemint.assignment.utils.Utils
import com.turtlemint.assignment.utils.launchActivity
import com.turtlemint.assignment.utils.showLoader
import com.turtlemint.assignment.viewmodel.LocalViewModel
import com.turtlemint.assignment.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val remoteViewModel by viewModel<RemoteViewModel>()
    private val localViewModel by viewModel<LocalViewModel>()
    private var issueRecyclerAdapter: IssueRecyclerAdapter? = null
    private val userList: MutableList<IssueEntity> = mutableListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observer()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvIssues.layoutManager = layoutManager
        binding.imageViewProgress.showLoader(true)
        remoteViewModel.getAllIssues()

    }

    private fun observer() {
        remoteViewModel.progressLiveData.observe(this) {
            binding.imageViewProgress.showLoader(false)
            getLocalData()
        }
    }

    private fun getLocalData() {
        localViewModel.getIssues().observe(this) { userListResource ->
            if (userListResource.error == null && userListResource.data != null) {
                userList.clear()
                userList.addAll(userListResource.data)
                issueRecyclerAdapter = IssueRecyclerAdapter(userList, object :
                    IssueRecyclerListener {
                    override fun clickIssue(issue: IssueEntity) {
                        if (userList.contains(issue)) {
                            callDetailsActivity(issue)
                        }
                    }
                })
                binding.rvIssues.adapter = issueRecyclerAdapter
            } else {
                val title = getString(R.string.error_title)
                val text = getString(R.string.error_retrieving_data)
                Utils.showSimpleDialog(this, title, text)
            }
        }
    }

    fun callDetailsActivity(item: IssueEntity) {
        val bundleData = HashMap<String, Any>()
        bundleData[Utils.ISSUE_OBJECT] = item
        launchActivity(DetailActivity::class.java, bundleData)
    }
}
