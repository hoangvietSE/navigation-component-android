package com.soict.hoangviet.navigationdoc.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    enum class AuthenticationState {
        UNAUTHENTICATED,
        AUTHENTICATED,
        INVALID_AUTHENTICATED
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var username: String = ""

    init {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username: String, password: String) {
        if (validateLogin(username, password)) {
            authenticationState.value = AuthenticationState.AUTHENTICATED
            this.username = username
        } else {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATED
        }
    }

    fun authenticate(authToken:String) {
        authenticationState.value = AuthenticationState.AUTHENTICATED
    }

    private fun validateLogin(username: String, password: String): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
    }
}