package presentation.base.threads

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Swing
actual val uiDispatcher: CoroutineDispatcher = Dispatchers.Main