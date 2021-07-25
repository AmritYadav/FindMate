package com.amydv.cache.rooms.entity

import androidx.room.*
import com.amydv.cache.rooms.utils.RoomConstants
import com.amydv.cache.rooms.utils.getMatchesStatus
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User

@Entity(tableName = RoomConstants.TABLE_USER)
data class UserEntity(
    val name: String,
    val gender: String,
    @PrimaryKey val dob: String,
    val age: Int,
    val email: String,
    val phone: String,
    val profile: String,
    @Embedded val location: UserUiModel.Location,
    val status: Int = -1
)

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        name = "${name.first} ${name.last}",
        gender = gender,
        dob = dob.date,
        age = dob.age,
        email = email,
        phone = phone,
        profile = picture.large,
        location = UserUiModel.Location(location.city, location.state, location.country),
        status = -1
    )
}

fun UserEntity.toUserUiModel(): UserUiModel {
    return UserUiModel(
        name = name,
        gender = gender,
        dob = dob,
        age = age,
        email = email,
        phone = phone,
        location = UserUiModel.Location(location.city, location.state, location.country),
        profileImage = profile,
        status = getMatchesStatus(status)
    )
}