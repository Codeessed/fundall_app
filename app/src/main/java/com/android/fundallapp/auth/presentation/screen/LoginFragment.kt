package com.android.fundallapp.auth.presentation.screen

import android.app.Dialog
import android.content.Intent
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
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.android.fundallapp.MainActivity
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.localdata.Authpreference
import com.android.fundallapp.auth.data.model.UserData
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
    private lateinit var email: String
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
        val userData = Authpreference.get<UserData>(Authpreference.AUTH_KEY)
        binding.userLoginDetail.isVisible = userData != null
        binding.loginEmailTil.isVisible = userData == null
        if (userData != null){
            binding.loginEmail.text = userData.email
            binding.loginSubtitle.text = getString(R.string.miss_you, userData.firstname)
            binding.loginUserImg.load(
                userData.avatar
            )
            email = userData.email
        }


        views()
        progressDialog = showProgressBar()

        observer(authViewModel.loginUser){ login ->
            progressDialog.dismiss()
            when(login){
                is AuthViewModel.AuthEvent.LoginSuccess -> {
                    Authpreference.put(login.result.success.user, Authpreference.AUTH_KEY)
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()
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
//            validate email text
//            validate password text
            if (!emailTil.isErrorEnabled && !passwordTil.isErrorEnabled){
                val loginRequest = LoginRequest(email, passwordEt.text.toString())
                authViewModel.signIn(loginRequest)
            }
        }

    }

    private fun views(){
        emailTil = binding.loginEmailTil
        emailEt = binding.loginEmailEt
        passwordTil = binding.loginPasswordTil
        passwordEt = binding.loginPasswordEt
        emailEt.addTextChangedListener {
            email = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}