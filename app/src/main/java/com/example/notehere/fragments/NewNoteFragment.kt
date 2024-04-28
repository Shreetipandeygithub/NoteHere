package com.example.notehere.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.example.notehere.MainActivity
import com.example.notehere.R
import com.example.notehere.adapter.NoteAdapter
import com.example.notehere.databinding.FragmentHomeBinding
import com.example.notehere.databinding.FragmentNewNoteBinding
import com.example.notehere.model.Note
import com.example.notehere.viewmodel.NoteViewModel


class NewNoteFragment : Fragment() {
    private var _binding: FragmentNewNoteBinding?=null
    private val binding get()=_binding!!

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var mView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel=(activity as MainActivity).notesViewModel
        mView=view
    }

    private fun saveNote(view: View){
        val noteTitle=binding.editNoteTitle.text.toString()
        val noteBody=binding.editNoteBody.text.toString()

        if (noteTitle.isNotEmpty()){
            val note=Note(0,noteTitle,noteBody)

            noteViewModel.addNote(note)

            Toast.makeText(mView.context
            ,"Note Saved Successfully",Toast.LENGTH_LONG).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }else{
            Toast.makeText(mView.context,"Please enter note Title",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuSave->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}