package com.turtlemint.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.turtlemint.assignment.databinding.ActivityDetailBinding
import com.turtlemint.assignment.datasource.local.database.entity.CommentEntity
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.ui.adapter.CommentsRecyclerAdapter
import com.turtlemint.assignment.utils.Utils
import com.turtlemint.assignment.utils.showLoader
import com.turtlemint.assignment.viewmodel.LocalViewModel
import com.turtlemint.assignment.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val remoteViewModel by viewModel<RemoteViewModel>()
    private val localViewModel by viewModel<LocalViewModel>()
    private var commentsRecyclerAdapter: CommentsRecyclerAdapter? = null
    private val commentsList: MutableList<CommentEntity> = mutableListOf()
    private var item = IssueEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        processIntent()
        init()
        observer()
    }

    private fun processIntent() {
        try {
            val intentMap =
                intent.getSerializableExtra(Utils.INTENT_MAP_DATA_KEY) as HashMap<*, *>?
            if (intentMap != null) {
                item = intentMap[Utils.ISSUE_OBJECT] as IssueEntity
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvComments.layoutManager = layoutManager
        binding.imageViewProgress.showLoader(true)

        binding.txtTitle.text = item.title
        binding.txtIssueStatus.text = item.state
        remoteViewModel.getComments(item.comments_url)
    }

    private fun observer() {
        remoteViewModel.progressLiveData.observe(this) {
            binding.imageViewProgress.showLoader(false)
            getLocalData()
        }
    }

    private fun getLocalData() {
        localViewModel.getComments().observe(this) { userListResource ->
            if (userListResource.error == null && userListResource.data != null) {
                commentsList.clear()
                commentsList.addAll(userListResource.data)
                commentsRecyclerAdapter = CommentsRecyclerAdapter(commentsList)
                binding.rvComments.adapter = commentsRecyclerAdapter
            } else {
                val title = getString(R.string.error_title)
                val text = getString(R.string.error_retrieving_data)
                Utils.showSimpleDialog(this, title, text)
            }
        }
    }
}