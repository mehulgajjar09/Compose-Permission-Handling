package com.example.composepermissionhandling

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val visiblePermissionDialogQueue = mutableListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeLast()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        visiblePermissionDialogQueue.add(0,permission)
    }
}