package com.fredrickjasin.todo.data.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("created_at")
    val createdAt: Long? = null,

    @SerialName("title")
    val title: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("media")
    val media: String = "",

    @SerialName("is_complete")
    val isComplete: Boolean = false,

    @SerialName("due_date")
    val dueDate: Long = 0L
)