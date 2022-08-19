package com.turtlemint.assignment.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turtlemint.assignment.R
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
            try {
                binding.txtIssueStatus.text = issueEntity.state
                binding.txtTitle.text = issueEntity.title
                binding.txtIssueType.text = issueEntity.label
                binding.txtDescription.text = issueEntity.body
                binding.txtUpdateTime.text =
                    "Updated at ${Utils.getFormattedDate(issueEntity.updated_at)}"
                binding.txtUser.text = "Posted by ${issueEntity.user}"

                binding.layoutRowIssue.setOnClickListener {
                    issueRecyclerClickListener?.clickIssue(issueEntity)
                }
                val statusColor = "#${issueEntity.label_color}"
                binding.txtIssueType.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor(statusColor))

                if (issueEntity.state == "open") {
                    binding.txtIssueStatus.setTextColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.red
                        )
                    )
                    binding.imgIssueStatus.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            parent.context.resources,
                            R.drawable.ic_baseline_radio_button_checked_24, null
                        )
                    )
                    binding.imgIssueStatus.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(parent.context, R.color.red))
                } else {
                    binding.txtIssueStatus.setTextColor(
                        ContextCompat.getColor(
                            parent.context,
                            R.color.grey
                        )
                    )
                    binding.imgIssueStatus.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            parent.context.resources,
                            R.drawable.ic_baseline_check_circle_24, null
                        )
                    )
                    binding.imgIssueStatus.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(parent.context, R.color.grey))
                }
                Glide.with(parent.context)
                    .load(issueEntity.avatar)
                    .fitCenter()
                    .centerCrop()
                    .into(binding.imgUser)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
