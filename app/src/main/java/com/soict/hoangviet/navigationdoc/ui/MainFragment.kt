package com.soict.hoangviet.navigationdoc.ui


import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.soict.hoangviet.navigationdoc.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun initViews() {
        btnNavigation.setOnClickListener {
            val action = MainFragmentDirections.navigateToProfileFragment()
            findNavController().navigate(action)
        }
    }

}
