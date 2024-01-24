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
import com.android.fundallapp.databinding.SignUpFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: Fragment() {
    private var _binding : SignUpFragmentBinding? = null
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
        _binding = SignUpFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        token = "JWT ${AuthPreference.getToken(TOKEN_KEY)}"
//        progressDialog = showProgressBar()

        val loginText = SpannableStringBuilder(getString(R.string.already_a_member))

        val clickableText = object : ClickableSpan() {

            override fun onClick(view: View) {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}