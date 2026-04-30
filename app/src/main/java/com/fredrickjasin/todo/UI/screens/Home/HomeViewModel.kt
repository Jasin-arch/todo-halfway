package com.fredrickjasin.todo.UI.screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredrickjasin.todo.data.Model.TodoModel
import com.fredrickjasin.todo.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = TodoRepository()

    // ================= STATE =================
    private val _todos = MutableStateFlow<TodoModel>(TodoModel())
    val todos = _todos.asStateFlow()

    private val _selectedTodo = MutableStateFlow(TodoModel())
    val selectedTodo = _selectedTodo.asStateFlow()

    init {
        getAllTodos()
    }

    // ================= CREATE =================
    fun createTodo(
        id: Int?,
        title: String,
        description: String,
        media: String,
        isComplete: Boolean,
        createdAt: Long,
        dueDate: Long
    ) {
        viewModelScope.launch {

            val todo = TodoModel(
                id = id,
                title = title,
                description = description,
                media = media,
                isComplete = isComplete,
                createdAt = createdAt,
                dueDate = dueDate
            )

            repository.createTask(todo)
            getAllTodos()
        }
    }

    // ================= READ ALL =================
    fun getAllTodos() {
        viewModelScope.launch {
            val result = repository.getAllTasks()
            _todos.value = result
        }
    }

    // ================= READ ONE =================
    fun getTodo(id: Int) {
        viewModelScope.launch {
            val result = repository.getTask(id)
            if (result != null) {
                _selectedTodo.value = result
            }
        }
    }

    // ================= UPDATE =================
    fun updateTodo(todo: TodoModel) {
        viewModelScope.launch {
            repository.updateTask(todo)
            getAllTodos()
        }
    }

    // ================= DELETE =================
    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            repository.deleteTask(id)
            getAllTodos()
        }
    }
}