package ipca.aulas.shopping.ui.listItems.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.aulas.shopping.ui.theme.Pink40
import ipca.aulas.shopping.ui.theme.Pink80

@Composable
fun AddItemView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    listId: String
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel: AddItemViewModel = viewModel()
        val state = viewModel.state

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.name ?: "",
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = {
                    Text(text = "insert item name")
                })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.qtd?.toString() ?: "",
                onValueChange = {
                    val qtd = it.toDouble()
                    viewModel.onQtdChange(qtd)
                },
                placeholder = {
                    Text(text = "insert quantity")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    viewModel.add(listId = listId)
                    navController.popBackStack()
                }) {
                    Text(text = "Add Item")
                }
                Log.d("AddItemView", "Received listId: $listId")
            }
        }
    }
}