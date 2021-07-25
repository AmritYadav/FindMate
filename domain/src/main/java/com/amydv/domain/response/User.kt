package com.amydv.domain.response

import com.amydv.domain.models.UserUiModel

data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val dob: Dob,
    val phone: String,
    val picture: Picture
) {
    data class Name(
        val title: String,
        val first: String,
        val last: String
    )

    data class Location(
        val city: String,
        val state: String,
        val country: String
    )

    data class Dob(
        val date: String,
        val age: Int
    )

    data class Picture(
        val large: String,
        val medium: String
    )
}

fun User.toUserUiModel() : UserUiModel {
    return UserUiModel(
        name = "${name.first} ${name.last}",
        gender = gender,
        dob = dob.date,
        age = dob.age,
        email = email,
        phone = phone,
        location = UserUiModel.Location(location.city, location.state, location.country),
        profileImage = picture.large
    )
}
