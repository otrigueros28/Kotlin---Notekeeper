package com.droid.notekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var notePosition =  Position_Not_Set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(Note_Position, Position_Not_Set) ?:
            intent.getIntExtra(Note_Position, Position_Not_Set)

        if(notePosition != Position_Not_Set)
            displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(Note_Position, notePosition)

    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu!!.findItem(R.id.action_next)
            if(menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
}