package com.example.android.architecture.blueprints.todoapp.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R

/**
 * Represents a New To Do screen
 */
object NewToDoScreen {
    val screen: ViewInteraction = onView(withId(R.id.add_task_layout))

    val titleField: ViewInteraction = onView(withId(R.id.add_task_title))
    val descriptionField: ViewInteraction = onView(withId(R.id.add_task_description))
    val addNewToDoButton: ViewInteraction = onView(ViewMatchers.withId(R.id.fab_edit_task_done))
}
