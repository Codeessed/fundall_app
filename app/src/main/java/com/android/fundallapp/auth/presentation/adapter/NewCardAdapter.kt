package com.android.fundallapp.auth.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.fundallapp.auth.data.model.NewCardItemModel
import com.android.fundallapp.databinding.NewCardItemBinding
import com.android.fundallapp.utils.OnCardItemClicked

class NewCardAdapter(private val onCardItemClicked: OnCardItemClicked): RecyclerView.Adapter<NewCardAdapter.NewCardViewHolder>() {
    inner class NewCardViewHolder(private val binding: NewCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val newCard = differ.currentList[position]
            binding.apply {
                newCardAmt.text = newCard.new_card_amt
                newCardIcon.load(
                    newCard.new_card_icon
                )
                cardCheck.isVisible = newCard.card_choice
                newCardName.text = newCard.new_card_name
                newCardView.setOnClickListener {
                    onCardItemClicked.cardClicked(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewCardViewHolder {
        return NewCardViewHolder(NewCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewCardViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private val differList = object: DiffUtil.ItemCallback<NewCardItemModel>(){
        override fun areItemsTheSame(
            oldItem: NewCardItemModel,
            newItem: NewCardItemModel
        ): Boolean {
            return oldItem.new_card_id == newItem.new_card_id
        }

        override fun areContentsTheSame(
            oldItem: NewCardItemModel,
            newItem: NewCardItemModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differList)

    interface OnItemClickListener{
        fun onClick(referenceCode: String)
    }
}