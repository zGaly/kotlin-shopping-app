package ipca.aulas.shopping.ui.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ipca.aulas.shopping.R
import ipca.aulas.shopping.ui.login.LoginViewModel
import ipca.aulas.shopping.ui.theme.Pink40
import ipca.aulas.shopping.ui.theme.Pink80
import ipca.aulas.shopping.ui.theme.ShoppingTheme

@Composable
fun AddListView(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel: AddListViewModel = viewModel()
        val state = viewModel.state

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = state.name ?: "",
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = {
                    Text(text = "insert list name")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box(modifier = Modifier
                    .background(
                        color = if (state.icon == 0L) Pink40 else Pink80,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        viewModel.onIconChange(0L)
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp),
                        painter = painterResource(R.drawable.baseline_architecture_24),
                        contentDescription = "Escola"
                    )
                }
                Box(modifier = Modifier
                    .background(
                        color = if (state.icon == 1L) Pink40 else Pink80,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        viewModel.onIconChange(1L)
                    }

                ) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp),
                        painter = painterResource(R.drawable.baseline_add_home_24),
                        contentDescription = "Casa"
                    )
                }
                Box(modifier = Modifier
                    .background(
                        color = if (state.icon == 2L) Pink40 else Pink80,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        viewModel.onIconChange(2L)
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp),
                        painter = painterResource(R.drawable.baseline_account_balance_wallet_24),
                        contentDescription = "Trabalho"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.add()
                    navController.popBackStack()
                }
            ) {
                Text(text = "ADD")
            }
            Spacer(modifier = Modifier.height(16.dp))
            state.error?.let {
                Text(text = it)
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddListViewPreview() {
    ShoppingTheme {
        AddListView()
    }
}
