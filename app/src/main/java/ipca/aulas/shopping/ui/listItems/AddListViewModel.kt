package ipca.aulas.shopping.ui.listItems

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import ipca.aulas.shopping.ui.models.ListItem
import ipca.aulas.shopping.ui.repository.ListItemRepository

data class AddListState(
    val name: String? = null,
    val icon: Long? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddListViewModel : ViewModel() {

    var state by mutableStateOf(AddListState())
        private set

    fun onNameChange(newValue:String){
        state = state.copy( name = newValue)
    }

    fun onIconChange(newValue:Long){
        state = state.copy( icon = newValue)
    }

    fun add() {
        state = state.copy(isLoading = true)
        val listItem = ListItem(
            name = state.name,
            icon = state.icon
        )
        ListItemRepository.add(listItem)
        state = state.copy(isLoading = false)
    }
}