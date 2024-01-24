package com.android.fundallapp.auth.data.model

import androidx.annotation.DrawableRes
import com.android.fundallapp.R

data class TransItemModel(
    val trans_place: String,
    val trans_icon: Int,
    val trans_number: Int,
    val trans_amt: String,
){
    companion object{
        val transItemList = mutableListOf(
            TransItemModel("Pizza Hut", R.drawable.ic_email, 4, "₦200"),
            TransItemModel("Amazon", R.drawable.ic_email,  3, "₦180"),
            TransItemModel("Subway", R.drawable.ic_email, 2, "₦125")
           )
    }
}
