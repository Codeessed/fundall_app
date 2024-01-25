package com.android.fundallapp.auth.presentation.screen.dashboard

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.android.fundallapp.MainActivity
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.localdata.Authpreference
import com.android.fundallapp.auth.data.model.UserData
import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.presentation.AuthViewModel
import com.android.fundallapp.databinding.HomeFragmentBinding
import com.android.fundallapp.databinding.LoginFragmentBinding
import com.android.fundallapp.utils.observer
import com.android.fundallapp.utils.showProgressBar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding : HomeFragmentBinding? = null
    private  val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userData = Authpreference.get<UserData>(Authpreference.AUTH_KEY)
        binding.analyticsCv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_analyticsFragment)
        }
        binding.requestCardCv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newCardFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}