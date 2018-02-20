package com.example.android.architecture.blueprints.todoapp.helper

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule

object AppHelper {
    @Rule
    @JvmField
    val tasksActivity = ActivityTestRule(TasksActivity::class.java, false, false)

    /**
     * Launches an app by starting home activity
     */
    fun launchApp() {
        tasksActivity.launchActivity(null)
    }

    /**
     * Closes an app by closing home activity
     */
    fun closeApp() {
        tasksActivity.finishActivity()
    }
}
