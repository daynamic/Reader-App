package com.akshat.readerapp.screens.details

import androidx.lifecycle.ViewModel
import com.akshat.readerapp.data.Resource
import com.akshat.readerapp.model.Item
import com.akshat.readerapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository):
ViewModel(){

    suspend fun getBookInfo(bookId: String): Resource<Item>{
        return repository.getBookInfo(bookId)
    }

}