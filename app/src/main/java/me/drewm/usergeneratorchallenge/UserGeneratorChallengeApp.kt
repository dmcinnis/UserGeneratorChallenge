package me.drewm.usergeneratorchallenge

import android.app.Application

class UserGeneratorChallengeApp : Application() {
    val appContainer: AppContainer by lazy {
        AppContainer()
    }
}
