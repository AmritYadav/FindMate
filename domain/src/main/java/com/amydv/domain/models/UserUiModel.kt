package com.amydv.domain.models

data class UserUiModel(
    val name: String,
    val gender: String,
    val dob: String,
    val age: Int,
    val email: String,
    val phone: String,
    val location: Location,
    val profileImage: String,
    var status: MatchesStatus = MatchesStatus.NONE
) {
    data class Location(
        val city: String,
        val state: String,
        val country: String
    )
}
