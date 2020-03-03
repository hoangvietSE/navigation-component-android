package com.soict.hoangviet.navigationdoc.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    enum class RegistrationState {
        COLLECT_PROFILE_DATA,
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED
    }

    val registrationState = MutableLiveData<RegistrationState>(RegistrationState.COLLECT_PROFILE_DATA)
    // Simulation of real-world scenario, where an auth token may be provided as
    // an alternate authentication mechanism instead of passing the password
    // around. This is set at the end of the registration process.
    var authToken = ""
        private set


    fun collectProfileData(fullName: String) {
        // ... validate and store data

        // Change State to collecting username and password
        registrationState.value = RegistrationState.COLLECT_USER_PASSWORD
    }

    fun createAccountAndLogin(username: String, password: String) {
        if(validateLogin(username,password)){
            // ... create account
            // ... authenticate
            this.authToken = ""
            // Change State to registration completed
            registrationState.value = RegistrationState.REGISTRATION_COMPLETED
        }
    }

    fun userCancelledRegistration(): Boolean {
        // Clear existing registration data
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
        authToken = ""
        return true
    }

    private fun validateLogin(username: String, password: String): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
    }
}