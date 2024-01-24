package com.android.fundallapp.auth.screen

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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.fundallapp.R
import com.android.fundallapp.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private var _binding : LoginFragmentBinding? = null
    private  val binding get()= _binding!!
//
//    private val kiddoViewModel: KiddoViewModel by activityViewModels()
//    private val utilityViewModel: UtilityViewModel by activityViewModels()
//    private var kidsList: ArrayList<KidDetails> = ArrayList()

    private lateinit var progressDialog: Dialog

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
//        token = "JWT ${AuthPreference.getToken(TOKEN_KEY)}"
//        progressDialog = showProgressBar()

        val signUpText = SpannableStringBuilder(getString(R.string.new_here))

        val clickableText = object : ClickableSpan() {

            override fun onClick(view: View) {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}