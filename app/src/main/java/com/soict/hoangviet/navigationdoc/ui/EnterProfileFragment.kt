package com.soict.hoangviet.navigationdoc.ui

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.soict.hoangviet.navigationdoc.R
import com.soict.hoangviet.navigationdoc.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_enter_profile.*
import java.lang.NullPointerException

/**
 * A simple [Fragment] subclass.
 */
class EnterProfileFragment : BaseFragment() {
    private val registrationViewModel: RegistrationViewModel by activityViewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_enter_profile

    override fun initListener() {
        btnNext.setOnClickListener {
            edtFullname.text.toString()?.let {
                registrationViewModel.collectProfileData(it)
            }
        }
        registrationViewModel.registrationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                RegistrationViewModel.RegistrationState.COLLECT_USER_PASSWORD -> {
                    findNavController().navigate(EnterProfileFragmentDirections.actionEnterProfileFragmentToRegisterFragment())
                }
            }
        })
    }

    override fun initData() {
    }

    override fun initViews() {
        onBackPress()
    }

    private fun onBackPress() {
        try {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                registrationViewModel.userCancelledRegistration()
                findNavController().popBackStack(R.id.loginFragment, false)
            }
        } catch (e: NullPointerException) {

        }
    }


}
