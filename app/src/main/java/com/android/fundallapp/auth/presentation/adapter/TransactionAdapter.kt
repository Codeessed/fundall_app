package com.android.fundallapp.auth.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.fundallapp.R
import com.android.fundallapp.auth.data.model.TransItemModel
import com.android.fundallapp.databinding.TransItemBinding

class TransactionAdapter(private val context: Context): RecyclerView.Adapter<TransactionAdapter.TransViewHolder>() {
    inner class TransViewHolder(private val binding: TransItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val trans = differ.currentList[position]
            binding.apply {
                transAmt.text = trans.trans_amt
                transNumber.text = context.getString(R.string.trans_amt_holder, trans.trans_number.toString())
                transPlace.text = trans.trans_place
                transIcon.load(
                    trans.trans_icon
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransViewHolder {
        return TransViewHolder(TransItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TransViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private val differList = object: DiffUtil.ItemCallback<TransItemModel>(){
        override fun areItemsTheSame(
            oldItem: TransItemModel,
            newItem: TransItemModel
        ): Boolean {
            return oldItem.trans_number == newItem.trans_number
        }

        override fun areContentsTheSame(
            oldItem: TransItemModel,
            newItem: TransItemModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differList)

    interface OnItemClickListener{
        fun onClick(referenceCode: String)
    }
}