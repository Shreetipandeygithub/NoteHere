package com.example.notehere.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notehere.MainActivity
import com.example.notehere.R
import com.example.notehere.databinding.FragmentUpdateNoteBinding
import com.example.notehere.model.Note
import com.example.notehere.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private var _binding: FragmentUpdateNoteBinding?=null
    private val binding get()=_binding!!

    private lateinit var noteViewModel: NoteViewModel

    private val arg:UpdateNoteFragmentArgs by navArgs()
    private lateinit var currenNote:Note


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentUpdateNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel=(activity as MainActivity).notesViewModel
        currenNote=arg.note!!

        binding.editNoteTitle.setText(currenNote.noteTitle)
        binding.editNoteBody.setText(currenNote.noteBody)


        //if the user update the note

        binding.floatingUpdateBtn.setOnClickListener {
            val title=binding.editNoteTitle.text.toString().trim()
            val body=binding.editNoteBody.text.toString().trim()

            if (title.isNotEmpty()){
                val note=Note(currenNote.id,title,body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)

            }else{
                Toast.makeText(context, "Please enter note Title", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do You want to delete the Node?")
            
            setPositiveButton("Delete"){_,_->
                noteViewModel.deleteNote(currenNote)

                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            }

            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuDelete->{
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {

        super.onDestroy()
        _binding=null
    }

}