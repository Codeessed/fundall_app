package com.android.fundallapp.auth.data.model

import androidx.annotation.DrawableRes
import com.android.fundallapp.R

data class NewCardItemModel(
    val new_card_id: Int,
    val new_card_name: String,
    val new_card_icon: Int,
    val new_card_amt: String,
    val card_choice: Boolean,
){
    companion object{
        val newCardItemList = mutableListOf(
            NewCardItemModel(1,"Lifestyle Pro", R.drawable.ic_email,  "₦9,500", false),
            NewCardItemModel(2,"Lifestyle Premium", R.drawable.ic_email,  "₦1,000", false),
            NewCardItemModel(3,"Lifestyle Business", R.drawable.ic_email, "₦1,200", false)
           )
    }
}
