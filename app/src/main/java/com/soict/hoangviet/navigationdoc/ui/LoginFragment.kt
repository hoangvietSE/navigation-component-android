package com.soict.hoangviet.navigationdoc.ui


import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.soict.hoangviet.navigationdoc.R
import com.soict.hoangviet.navigationdoc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment() {
    // Get a reference to the ViewModel scoped to its Activity
    private val loginViewModel: LoginViewModel by activityViewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun initListener() {
        btnLogin.setOnClickListener {
            loginViewModel.authenticate(
                edtUsername.text.toString(),
                edtPassword.text.toString()
            )
        }
        btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.navigateToEnterProfile())
        }
        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> findNavController().popBackStack()

                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATED -> showErrorLogin()
            }
        })
    }

    private fun showErrorLogin() {
        Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
    }

    override fun initData() {
        initViewModel()
    }

    private fun initViewModel() {

    }

    override fun initViews() {
        onBackPress()
    }

    private fun onBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            loginViewModel.refuseAuthentication()
            findNavController().popBackStack(R.id.mainFragment, false)
        }

    }

}