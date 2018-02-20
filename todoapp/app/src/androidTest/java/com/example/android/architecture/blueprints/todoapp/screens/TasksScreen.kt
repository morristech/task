package com.example.android.architecture.blueprints.todoapp.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.core.AllOf.allOf

/**
 * Represents a home screen with tasks list
 */
object TasksScreen {
    val screen: ViewInteraction = onView(withId(R.id.tasks_layout))
    val addNewToDoButton: ViewInteraction = onView(withId(R.id.fab_add_task))

    fun note(title: String) = onView(allOf(isDescendantOfA(withId(R.id.task_item)), withText(title)))
}
