package com.droid.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init{
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){
        var course = CourseInfo(courseId = "android_intents", title =  "Android programming with intents")
        courses.set(course.courseId, course)

        course = CourseInfo(courseId = "adnroid_async", title = "android async programming")
        courses.set(course.courseId, course)

        course = CourseInfo(courseId = "jave_lang", title = "Java fundamentals")
        courses.set(course.courseId, course)

        course = CourseInfo(courseId = "java_core", title = "Java Core platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes (){
        notes.add(NoteInfo(courses["android_intents"]!!, "TEST1", "TEST1"))
        notes.add(NoteInfo(courses["android_async"]!!, "TEST2", "TEST2"))
        notes.add(NoteInfo(courses["android_intents"]!!, "TEST3", "TEST3"))
        notes.add(NoteInfo(courses["java_fundementals"]!!, "TEST4", "TEST4"))
        notes.add(NoteInfo(courses["java_fundementals"]!!, "TEST5", "TEST5"))
        notes.add(NoteInfo(courses["java_fundementals"]!!, "TEST6", "TEST6"))
    }
}