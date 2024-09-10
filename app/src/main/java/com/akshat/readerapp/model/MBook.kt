package com.akshat.readerapp.model

data class MBook(
    var id: String? = null,
    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,
    var photoUrl: String? = null,
    var categories: String? = null,
    var rating: Double? = null,
) {

}
