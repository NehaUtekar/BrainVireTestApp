package com.brainvire.brainviretestapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainResponsePojo (
    @SerializedName("rates")
    @Expose
    var rates: HashMap<String, HashMap<String,Double>>?,
    @SerializedName("start_at")
    @Expose
    var startAt:String?,
    @SerializedName("base")
    @Expose
    var base:String?,
    @SerializedName("end_at")
    @Expose
    var endAt:String?

){
}