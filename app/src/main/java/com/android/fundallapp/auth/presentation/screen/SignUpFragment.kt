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
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.model.signup.SignUpRequest
import com.android.fundallapp.auth.presentation.AuthViewModel
import com.android.fundallapp.databinding.LoginFragmentBinding
import com.android.fundallapp.databinding.SignUpFragmentBinding
import com.android.fundallapp.utils.observer
import com.android.fundallapp.utils.showProgressBar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: Fragment() {
    private var _binding : SignUpFragmentBinding? = null
    private  val binding get()= _binding!!

    private val authViewModel: AuthViewModel by activityViewModels()
//    private var kidsList: ArrayList<KidDetails> = ArrayList()

    private lateinit var firstNameTil: TextInputLayout
    private lateinit var lastNameTil: TextInputLayout
    private lateinit var emailTil: TextInputLayout
    private lateinit var passwordTil: TextInputLayout
    private lateinit var confirmPasswordTil: TextInputLayout
    private lateinit var firstNameEt: TextInputEditText
    private lateinit var lastNameEt: TextInputEditText
    private lateinit var emailEt: TextInputEditText
    private lateinit var passwordEt: TextInputEditText
    private lateinit var confirmPasswordEt: TextInputEditText

    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = showProgressBar()

        views()
        observer(authViewModel.addUser){ signUp ->
            progressDialog.dismiss()
            when(signUp){
                is AuthViewModel.AuthEvent.SignUpSuccess -> {
                    Toast.makeText(requireContext(), signUp.result.toString(), Toast.LENGTH_SHORT).show()
                }
                is AuthViewModel.AuthEvent.Loading -> {
                    progressDialog.show()
                }
                is AuthViewModel.AuthEvent.Failure -> {
                    Toast.makeText(requireContext(), signUp.errorText, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }

        val loginText = SpannableStringBuilder(getString(R.string.already_a_member))

        val clickableText = object : ClickableSpan() {

            override fun onClick(view: View) {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }
        loginText.setSpan(
            clickableText,
            18,
            loginText.length,
            0
        )
        loginText.setSpan(
            ForegroundColorSpan(Color.BLACK),
            18,
            loginText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        loginText.setSpan(
            RelativeSizeSpan(1.2f),
            18,
            loginText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        loginText.setSpan(
            Typeface.BOLD,
            18,
            loginText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.signLoginTv.setText(loginText, TextView.BufferType.SPANNABLE)
        binding.signLoginTv.isClickable = true
        binding.signLoginTv.movementMethod = LinkMovementMethod.getInstance()

        val termsText = SpannableStringBuilder(getString(R.string.terms_conditions))

        termsText.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.primary)),
            40,
            59,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        termsText.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.primary)),
            63,
            termsText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.signUpBtmText.setText(termsText, TextView.BufferType.SPANNABLE)

        binding.signupButton.setOnClickListener {
            val signUpRequest = SignUpRequest(firstNameEt.text.toString(), lastNameEt.text.toString(), emailEt.text.toString(), passwordEt.text.toString(), confirmPasswordEt.text.toString())
            authViewModel.signUp(signUpRequest)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun views(){
        firstNameTil = binding.signupFirstnameTil
        firstNameEt = binding.signupFirstnameEt
        lastNameTil = binding.signupLastnameTil
        lastNameEt = binding.signupLastnameEt
        emailTil = binding.signupEmailTil
        emailEt = binding.signupEmailEt
        passwordTil = binding.signupPasswordTil
        passwordEt = binding.signupPasswordEt
        confirmPasswordTil = binding.signupConfirmPasswordTil
        confirmPasswordEt = binding.signupConfirmPasswordEt
    }


}