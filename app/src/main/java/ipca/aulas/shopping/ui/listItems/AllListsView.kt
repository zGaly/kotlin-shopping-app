package ipca.aulas.shopping.ui.listItems

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.aulas.shopping.R
import ipca.aulas.shopping.ui.models.ListItem
import ipca.aulas.shopping.ui.theme.ShoppingTheme

@Composable
fun AllListView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    onClickListItem: (String?) -> Unit = {}
) {

    val viewModel: AllListsViewModel = viewModel()
    val state = viewModel.state


    AllListViewContent(
        state = state,
        navController = navController,
        onClickListItem = { listId ->
            navController.navigate("items/$listId")
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllLists()
    }

}

@Composable
fun AllListViewContent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    state: AllListState,
    onClickListItem: (String?) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (state.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.error)
            }
        } else {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                itemsIndexed(
                    items = state.listItems
                ) { _, item ->
                    RowListItem(
                        modifier = Modifier
                            .clickable {
                                onClickListItem(item.docId)
                            },
                        listItem = item
                    )
                }
            }
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                navController.navigate("add_list")
            }) {
            Text("ADD LIST")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllListViewPreview() {
    ShoppingTheme {
        AllListViewContent(
            state =
            AllListState(
                isLoading = false,
                error = "internet error",
                listItems = listOf(
                    ListItem(
                        name = "Escola",
                        icon = 0L
                    )
                )
            )
        )
    }
}