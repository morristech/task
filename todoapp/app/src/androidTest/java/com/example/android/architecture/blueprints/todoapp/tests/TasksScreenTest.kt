package com.example.android.architecture.blueprints.todoapp.tests

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.android.architecture.blueprints.todoapp.helper.AppHelper
import com.example.android.architecture.blueprints.todoapp.screens.NewToDoScreen
import com.example.android.architecture.blueprints.todoapp.screens.TasksScreen
import org.junit.Ignore
import org.junit.Test

class TasksScreenTest {
    @Test
    fun testCanCreateNewToDo() {
        AppHelper.launchApp()

        TasksScreen.screen.check(matches(isDisplayed()))
        waitForDataLoaded()

        TasksScreen.addNewToDoButton.perform(click())

        NewToDoScreen.screen.check(matches(isDisplayed()))
        NewToDoScreen.titleField.perform(typeText("test"))
        NewToDoScreen.descriptionField.perform(typeText("description"))
        NewToDoScreen.addNewToDoButton.perform(click())

        TasksScreen.screen.check(matches(isDisplayed()))
        waitForDataLoaded()

        TasksScreen.note("test").check(matches(isDisplayed()))

        // TODO: additionally, open created todo and verify the title and the description

        AppHelper.closeApp()
    }

    @Ignore
    @Test
    fun testCanDeleteExistingTodo() {
        TODO("implement")
    }

    @Ignore
    @Test
    fun testCanUpdateExistingToDo() {
        TODO("implement")
    }

    private fun waitForDataLoaded() {
        // TODO: Loading all tasks is slow, use a sleep temporary
        Thread.sleep(3_000L)
    }
}
