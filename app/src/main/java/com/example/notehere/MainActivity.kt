package com.example.notehere

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notehere.database.NoteDatabase
import com.example.notehere.databinding.ActivityMainBinding
import com.example.notehere.repository.NoteRepository
import com.example.notehere.viewmodel.NoteViewModel
import com.example.notehere.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var notesViewModel:NoteViewModel

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpViewModel() {
        val noteRepository=NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory=NoteViewModelFactory(application,noteRepository)
        notesViewModel=ViewModelProvider(
            this,viewModelProviderFactory
        ).get(NoteViewModel::class.java)
    }
}