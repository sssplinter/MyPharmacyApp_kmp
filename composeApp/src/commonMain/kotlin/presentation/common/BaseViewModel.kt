package presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

open class BaseViewModel : ViewModel(){
    val scope = this.viewModelScope
}
