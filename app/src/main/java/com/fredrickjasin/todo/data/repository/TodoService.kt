package com.fredrickjasin.todo.data.repository

import com.fredrickjasin.todo.data.Model.TodoModel

interface TodoService {

    suspend fun createTask(todo: TodoModel): TodoModel?

    suspend fun getAllTasks(): List<TodoModel>

    suspend fun getTask(id: Int): TodoModel?

    suspend fun updateTask(todo: TodoModel): TodoModel?

    suspend fun deleteTask(id: Int): Boolean
}