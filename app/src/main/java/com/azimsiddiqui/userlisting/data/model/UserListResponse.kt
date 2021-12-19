package com.azimsiddiqui.userlisting.data.model

data class UserListResponse(
    val data:List<User>,
    val total:Int,
    val page:Int,
    val limit:Int
)