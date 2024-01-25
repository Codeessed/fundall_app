package com.android.fundallapp.auth.presentation.screen.dashboard

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.opengl.Visibility
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
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.android.fundallapp.MainActivity
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.localdata.Authpreference
import com.android.fundallapp.auth.data.model.TransItemModel.Companion.transItemList
import com.android.fundallapp.auth.data.model.UserData
import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.presentation.AuthViewModel
import com.android.fundallapp.auth.presentation.adapter.TransactionAdapter
import com.android.fundallapp.databinding.AnalyticsFragmentBinding
import com.android.fundallapp.databinding.LoginFragmentBinding
import com.android.fundallapp.utils.observer
import com.android.fundallapp.utils.showProgressBar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalyticsFragment: Fragment() {
    private var _binding : AnalyticsFragmentBinding? = null
    private  val binding get()= _binding!!

    private val authViewModel: AuthViewModel by activityViewModels()

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
        _binding = AnalyticsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.compare.setOnClickListener {
            binding.compareText.visibility = View.INVISIBLE
            binding.compareCard.visibility = View.VISIBLE

            binding.insightCard.visibility = View.INVISIBLE
            binding.insightText.visibility = View.VISIBLE
        }

        binding.insight.setOnClickListener {
            binding.compareText.visibility = View.VISIBLE
            binding.compareCard.visibility = View.INVISIBLE

            binding.insightCard.visibility = View.VISIBLE
            binding.insightText.visibility = View.INVISIBLE
        }
        binding.analyticsBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val transRecycler = binding.transRecycler
        val transAdapter = TransactionAdapter(requireContext())
        transRecycler.adapter = transAdapter
        transRecycler.layoutManager = LinearLayoutManager(requireContext())
        transAdapter.differ.submitList(transItemList)

    }

    private fun views(){
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}