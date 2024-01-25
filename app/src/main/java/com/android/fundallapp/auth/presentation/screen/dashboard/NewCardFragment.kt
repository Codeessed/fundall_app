package com.android.fundallapp.auth.presentation.screen.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.fundallapp.R
import com.android.fundallapp.databinding.NewCardFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCardFragment: Fragment(){
    private var _binding : NewCardFragmentBinding? = null
    private  val binding get()= _binding!!

    private var isfirstCard = false
    private var isSecondCard = false
    private var isThirdCard = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewCardFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstCard.setOnClickListener {
            isfirstCard = true
            isSecondCard = false
            isThirdCard = false
            pickCard()
        }

        binding.secondCard.setOnClickListener {
            isfirstCard = false
            isSecondCard = true
            isThirdCard = false
            pickCard()
        }

        binding.thirdCard.setOnClickListener {
            isfirstCard = false
            isSecondCard = false
            isThirdCard = true
            pickCard()
        }

        binding.newCardBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.newCardBtn.setOnClickListener {
            if (!isfirstCard && !isSecondCard && !isThirdCard){
                Toast.makeText(
                    requireContext(),
                    "Please pick a card to continue",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog)
                    .create()
                val view = layoutInflater.inflate(R.layout.new_card_success_dialog,null)
                val  cancel = view.findViewById<CardView>(R.id.cancel)
                val  refer = view.findViewById<CardView>(R.id.refer_btn)
                builder.setView(view)
                cancel.setOnClickListener {
                    builder.dismiss()
                }
                refer.setOnClickListener {
                    builder.dismiss()
                }
                builder.setCanceledOnTouchOutside(false)
                builder.show()
            }
        }

    }

    private fun pickCard(){
        binding.firstCardCheck.isVisible = isfirstCard
        binding.secondCardCheck.isVisible = isSecondCard
        binding.thirdCardCheck.isVisible = isThirdCard
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}