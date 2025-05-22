package ipca.aulas.shopping.ui.listItems.items

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ipca.aulas.shopping.ui.models.Item
import ipca.aulas.shopping.ui.models.ListItem
import ipca.aulas.shopping.ui.repository.ItemRepository

data class AddItemState(
    val name: String? = null,
    val qtd: Double? = null,
    val checked: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddItemViewModel : ViewModel() {

    var state by mutableStateOf(AddItemState())
        private set

    fun onNameChange(newValue: String) {
        state = state.copy(name = newValue)
    }

    fun onQtdChange(newValue: Double) {
        state = state.copy(qtd = newValue)
    }

    fun onCheckedChange(newValue: Boolean) {
        state = state.copy(checked = newValue)
    }

    fun add(listId: String) {
        state = state.copy(isLoading = true)
        val item = Item(
            name = state.name,
            qtd = state.qtd,
            checked = state.checked
        )
        Log.d("AddItemViewModel", "Attempting to add item: $item to list: $listId")
        ItemRepository.add(listId, item)
        state = state.copy(isLoading = false)
    }
}