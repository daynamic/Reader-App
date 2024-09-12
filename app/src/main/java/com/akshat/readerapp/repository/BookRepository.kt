package com.akshat.readerapp.repository

import com.akshat.readerapp.data.DataOrException
import com.akshat.readerapp.data.Resource
import com.akshat.readerapp.model.Item
import com.akshat.readerapp.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi) {

    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()

    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()

    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {

       return try {
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
           if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)

        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }

    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
       val response = try {
           Resource.Loading(data = true)
           api.getBookInfo(bookId)
        } catch (e: Exception) {
           return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}