package com.turtlemint.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turtlemint.assignment.databinding.ActivityDetailBinding
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.utils.Utils
import com.turtlemint.assignment.viewmodel.LocalViewModel
import com.turtlemint.assignment.viewmodel.RemoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val remoteViewModel by viewModel<RemoteViewModel>()
    private val localViewModel by viewModel<LocalViewModel>()

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
        remoteViewModel.getComments(item.comments_url)
    }

    private fun observer() {

    }
}