package com.example.atlysassignment.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun EditText.textUpdate(): Flow<CharSequence?> =
    callbackFlow {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        addTextChangedListener(watcher)
        awaitClose {
            removeTextChangedListener(watcher)
        }
    }.onStart {
        emit(text)
    }
