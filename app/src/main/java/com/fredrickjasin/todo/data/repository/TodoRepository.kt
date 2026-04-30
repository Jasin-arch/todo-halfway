package com.fredrickjasin.todo.data.repository

import android.util.Log
import com.fredrickjasin.todo.data.Model.TodoModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class TodoRepository : TodoService {

    private val supabase = createSupabaseClient(
        supabaseUrl = "https://pweitsumlvptipuizwpe.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InB3ZWl0c3VtbHZwdGlwdWl6d3BlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc0MTMzMjEsImV4cCI6MjA5Mjk4OTMyMX0.OpU9Ypqe3gx5eVIWrPtpQteVw5Nr1j7g8q1L7DiOVOA"
    ) {
        install(Postgrest)
    }

    // ================= CREATE =================
    override suspend fun createTask(todo: TodoModel): TodoModel? {
        return try {
            val result = supabase
                .from("todo") // ✅ FIXED
                .insert(todo) { select() }
                .decodeSingle<TodoModel>()

            Log.d("SUPABASE", "Created: $result")
            result

        } catch (e: Exception) {
            Log.e("SUPABASE", "Create failed", e)
            null
        }
    }

    // ================= READ ALL =================
    override suspend fun getAllTasks(): List<TodoModel> {
        return try {
            val result = supabase
                .from("todo") // ✅ FIXED
                .select()
                .decodeList<TodoModel>()

            Log.d("SUPABASE", "Fetched ${result.size} tasks")
            result

        } catch (e: Exception) {
            Log.e("SUPABASE", "Fetch all failed", e)
            emptyList()
        }
    }

    // ================= READ ONE =================
    override suspend fun getTask(id: Int): TodoModel? {
        return try {
            supabase
                .from("todo") // ✅ FIXED
                .select {
                    filter { eq("id", id) }
                }
                .decodeAsOrNull<TodoModel>()

        } catch (e: Exception) {
            Log.e("SUPABASE", "Fetch one failed", e)
            null
        }
    }

    // ================= UPDATE =================
    override suspend fun updateTask(todo: TodoModel): TodoModel? {
        val id = todo.id ?: return null

        return try {
            supabase
                .from("todo") // ✅ FIXED
                .update(todo) {
                    select()
                    filter { eq("id", id) }
                }
                .decodeSingle<TodoModel>()

        } catch (e: Exception) {
            Log.e("SUPABASE", "Update failed", e)
            null
        }
    }

    // ================= DELETE =================
    override suspend fun deleteTask(id: Int): Boolean {
        return try {
            supabase
                .from("todo") // ✅ FIXED
                .delete {
                    filter { eq("id", id) }
                }

            true

        } catch (e: Exception) {
            Log.e("SUPABASE", "Delete failed", e)
            false
        }
    }
}