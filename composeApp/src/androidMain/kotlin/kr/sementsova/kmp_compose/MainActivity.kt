package kr.sementsova.kmp_compose

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import common.MedicinesViewModel
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import repository.MedicinesRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val medicinesRepository = MedicinesRepository(DatabaseDriverFactory(this))
        val viewModel = MedicinesViewModel(medicinesRepository)

        setContent {
            App(medicinesRepository, viewModel)
        }
    }
}
