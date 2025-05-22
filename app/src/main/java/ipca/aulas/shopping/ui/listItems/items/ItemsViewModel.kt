package ipca.aulas.shopping.ui.listItems.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ipca.aulas.shopping.ui.models.Item
import ipca.aulas.shopping.ui.models.ListItem
import ipca.aulas.shopping.ui.repository.ItemRepository

data class ItemsState(
    val items: List<Item> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ItemsViewModel  : ViewModel(){

    var state by mutableStateOf(ItemsState())
        private set

    fun getAll(listId: String) {
        state = state.copy(isLoading = true)
        ItemRepository.getAll(listId) { items, error ->
            if (error != null) {
                state = state.copy(isLoading = false, error = error)
            } else {
                state = state.copy(isLoading = false, items = items) // Update state with items
            }
        }
    }

    fun check(listId: String, item: Item) {
        val updatedItem = item.copy(checked = !(item.checked ?: false)) // Toggle checked state
        ItemRepository.update(listId, updatedItem) // Update in Firebase
    }
}