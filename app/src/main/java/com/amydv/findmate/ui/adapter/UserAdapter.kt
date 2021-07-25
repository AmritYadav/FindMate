package com.amydv.findmate.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amydv.domain.models.UserUiModel
import java.util.*

typealias UserActionCallback = (userUiModel: UserUiModel) -> Unit

class UserAdapter(private val callback: UserActionCallback) : ListAdapter<UserUiModel, UserViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    internal class UserDiffUtil : DiffUtil.ItemCallback<UserUiModel>() {

        override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
            return Objects.equals(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
            return oldItem.dob == newItem.dob && oldItem.email == oldItem.email
        }
    }
}