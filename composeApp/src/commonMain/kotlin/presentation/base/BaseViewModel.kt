package presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class BaseViewModel : ViewModel(){
    val scope = this.viewModelScope
}
