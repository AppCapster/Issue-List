package com.turtlemint.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turtlemint.assignment.databinding.ItemCommentBinding
import com.turtlemint.assignment.datasource.local.database.entity.CommentEntity
import com.turtlemint.assignment.utils.Utils

class CommentsRecyclerAdapter(var issues: List<CommentEntity>) :
    RecyclerView.Adapter<CommentsRecyclerAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    inner class CommentViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemCommentBinding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commentEntity: CommentEntity) {
            binding.txtDescription.text = commentEntity.body

            binding.txtUpdateTime.text =
                "Commented ${Utils.getFormattedDate(commentEntity.updated_at)}"
            binding.txtUser.text = commentEntity.user
        }
    }
}
