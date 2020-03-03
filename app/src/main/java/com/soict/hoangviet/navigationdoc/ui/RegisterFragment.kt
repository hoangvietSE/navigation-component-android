package com.soict.hoangviet.navigationdoc.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.soict.hoangviet.navigationdoc.R
import com.soict.hoangviet.navigationdoc.viewmodel.LoginViewModel
import com.soict.hoangviet.navigationdoc.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : BaseFragment() {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val registrationViewModel: RegistrationViewModel by activityViewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_register

    override fun initListener() {
        btnLogin.setOnClickListener {
            registrationViewModel.createAccountAndLogin(
                edtUsername.text.toString(),
                edtPassword.text.toString()
            )
        }
        registrationViewModel.registrationState.observe(
            viewLifecycleOwner, Observer { state ->
                if (state == RegistrationViewModel.RegistrationState.REGISTRATION_COMPLETED) {
                    // Here we authenticate with the token provided by the ViewModel
                    // then pop back to the profie_fragment, where the user authentication
                    // status will be tested and should be authenticated.
                    val authToken = registrationViewModel.authToken
                    loginViewModel.username = edtUsername.text.toString()
                    loginViewModel.authenticate(authToken)
                    findNavController().popBackStack(R.id.profileFragment, false)
                }
            })
    }

    override fun initData() {

    }

    override fun initViews() {
        onBackPress()
    }

    private fun onBackPress() {
        registrationViewModel.userCancelledRegistration()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.loginFragment, false)
        }
    }
}
