package com.droid.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        fab.setOnClickListener {
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        listNotes.adapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,
        DataManager.notes)

        listNotes.setOnItemClickListener{parent, view, position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(Note_Position, position)
            startActivity(activityIntent)
        }


    }

    override fun onResume() {
        super.onResume()
        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}