package com.azimsiddiqui.userlisting.data

data class UserListResponse(
    val data:List<User>,
    val total:Int,
    val page:Int,
    val limit:Int
)