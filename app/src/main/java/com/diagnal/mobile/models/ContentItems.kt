package com.diagnal.mobile.models

import com.google.gson.annotations.SerializedName

data class ContentItems (

@SerializedName("content" ) var content : ArrayList<VideoDetail> = arrayListOf()

)
