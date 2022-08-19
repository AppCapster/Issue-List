package com.turtlemint.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turtlemint.assignment.databinding.ItemIssueBinding
import com.turtlemint.assignment.datasource.local.database.entity.IssueEntity
import com.turtlemint.assignment.ui.listener.IssueRecyclerListener
import com.turtlemint.assignment.utils.Utils

class IssueRecyclerAdapter(
    var issues: List<IssueEntity>,
    private val issueRecyclerClickListener: IssueRecyclerListener?
) :
    RecyclerView.Adapter<IssueRecyclerAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        return IssueViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    inner class IssueViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemIssueBinding = ItemIssueBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueEntity: IssueEntity) {
            binding.txtTitle.text = issueEntity.title
            binding.txtIssueStatus.text = issueEntity.label
            binding.txtDescription.text = issueEntity.body
            binding.txtUpdateTime.text = "Updated at ${Utils.getFormattedDate(issueEntity.updated_at)}"
            binding.txtUser.text = "Posted by ${issueEntity.user}"

            binding.layoutRowIssue.setOnClickListener {
                issueRecyclerClickListener?.clickIssue(issueEntity)
            }
        }
    }
}
