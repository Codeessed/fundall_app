package com.android.fundallapp.auth.presentation.screen

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.presentation.AuthViewModel
import com.android.fundallapp.databinding.LoginFragmentBinding
import com.android.fundallapp.utils.observer
import com.android.fundallapp.utils.showProgressBar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private var _binding : LoginFragmentBinding? = null
    private  val binding get()= _binding!!
//
//    private val kiddoViewModel: KiddoViewModel by activityViewModels()
private val authViewModel: AuthViewModel by activityViewModels()
//    private var kidsList: ArrayList<KidDetails> = ArrayList()

    private lateinit var progressDialog: Dialog

    private lateinit var emailTil: TextInputLayout
    private lateinit var passwordTil: TextInputLayout
    private lateinit var emailEt: TextInputEditText
    private lateinit var passwordEt: TextInputEditText

    private lateinit var token: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views()
        progressDialog = showProgressBar()

        observer(authViewModel.loginUser){ login ->
            progressDialog.dismiss()
            when(login){
                is AuthViewModel.AuthEvent.LoginSuccess -> {
                    binding.loginUserImg.load(
                        login.result.success.user.avatar
                    )
                    Toast.makeText(requireContext(), login.result.toString(), Toast.LENGTH_SHORT).show()
                }
                is AuthViewModel.AuthEvent.Loading -> {
                    progressDialog.show()
                }
                is AuthViewModel.AuthEvent.Failure -> {
                    Toast.makeText(requireContext(), login.errorText, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }

        val signUpText = SpannableStringBuilder(getString(R.string.new_here))

        val clickableText = object : ClickableSpan() {

            override fun onClick(view: View) {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }
        signUpText.setSpan(
            clickableText,
            10,
            signUpText.length,
            0
        )
        signUpText.setSpan(
            ForegroundColorSpan(Color.BLACK),
            10,
            signUpText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        signUpText.setSpan(
            RelativeSizeSpan(1.2f),
            10,
            signUpText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        signUpText.setSpan(
            Typeface.BOLD,
            10,
            signUpText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.loginSignupTv.setText(signUpText, TextView.BufferType.SPANNABLE)
        binding.loginSignupTv.isClickable = true
        binding.loginSignupTv.movementMethod = LinkMovementMethod.getInstance()

        binding.signinButton.setOnClickListener {
            val loginRequest = LoginRequest(emailEt.text.toString(), passwordEt.text.toString())
            authViewModel.signIn(loginRequest)
        }

    }

    private fun views(){
        emailTil = binding.loginEmailTil
        emailEt = binding.loginEmailEt
        passwordTil = binding.loginPasswordTil
        passwordEt = binding.loginPasswordEt
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}