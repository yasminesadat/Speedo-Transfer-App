package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.model.User

class UserSource {
    fun getUsers() :List<User>{
        val users = mutableListOf<User>().apply {
            add( User("Mohamed", "moh@gmail.com", "12345678Aa.", "","" ,listOf(), listOf()))

        }
        return users
    }
}