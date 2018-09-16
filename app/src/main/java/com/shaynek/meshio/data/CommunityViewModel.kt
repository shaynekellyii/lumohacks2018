package com.shaynek.meshio.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel : ViewModel() {
    val messages: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun addMessage(message: String) = messages.value?.add(message)
}