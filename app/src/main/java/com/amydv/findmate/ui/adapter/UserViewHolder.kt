package com.amydv.findmate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amydv.domain.models.MatchesStatus
import com.amydv.domain.models.UserUiModel
import com.amydv.findmate.databinding.ItemUserBinding
import com.amydv.findmate.utils.loadImage
import com.amydv.findmate.utils.setVisible

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userUiModel: UserUiModel, callback: UserActionCallback) {
        with(userUiModel) {
            binding.ivUser.loadImage(profileImage)
            binding.name.text = name
            val otherDetails = "$age, ${location.city}, ${location.state}"
            binding.others.text = otherDetails

            val showActions = status == MatchesStatus.NONE
            binding.grpAction.setVisible(showActions)
            binding.status.setVisible(!showActions)
            if (!showActions) {
                val status = "Member ${status.label}"
                binding.status.text = status
            }
        }

        binding.ivDecline.setOnClickListener {
            val matchesStatus = MatchesStatus.DECLINED
            userUiModel.status = matchesStatus
            updateUi(matchesStatus)
            callback.invoke(userUiModel)
        }

        binding.ivAccept.setOnClickListener {
            val matchesStatus = MatchesStatus.ACCEPTED
            userUiModel.status = matchesStatus
            updateUi(matchesStatus)
            callback.invoke(userUiModel)
        }
    }

    companion object {
        fun create(parent: ViewGroup) : UserViewHolder {
            return UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    private fun updateUi(matchesStatus: MatchesStatus) {
        binding.grpAction.setVisible(false)
        binding.status.setVisible(true)
        val status = "Member ${matchesStatus.label}"
        binding.status.text = status
    }
}