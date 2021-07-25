package com.amydv.cache.rooms.utils

import com.amydv.domain.models.MatchesStatus

object RoomConstants {
    const val TABLE_USER = "user"
}

fun getMatchesStatus(status: Int): MatchesStatus {
    return when (status) {
        0 -> MatchesStatus.DECLINED
        1 -> MatchesStatus.ACCEPTED
        else -> MatchesStatus.NONE
    }
}

fun getStatus(matchesStatus: MatchesStatus): Int {
    return when (matchesStatus) {
        MatchesStatus.ACCEPTED -> 1
        MatchesStatus.DECLINED -> 0
        MatchesStatus.NONE -> -1
    }
}