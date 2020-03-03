package com.soict.hoangviet.navigationdoc.ui


import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.soict.hoangviet.navigationdoc.R
import com.soict.hoangviet.navigationdoc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment() {
    private val mLoginViewModel: LoginViewModel by activityViewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun initViews() {
        mLoginViewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> {
                    navigateLoginScreen()
                }
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    showProfile()
                }
            }
        })

    }

    private fun navigateLoginScreen() {
        findNavController().navigate(R.id.loginFragment)
    }

    private fun showProfile() {
        tvName.text = mLoginViewModel.username
    }


}
