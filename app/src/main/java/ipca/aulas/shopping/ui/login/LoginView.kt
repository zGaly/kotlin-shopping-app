package ipca.aulas.shopping.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ipca.aulas.shopping.ui.theme.ShoppingTheme

@Composable
fun LoginView(modifier: Modifier = Modifier,
              navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel: LoginViewModel = viewModel()
        val state = viewModel.state
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.email ?: "",
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = {
                    Text("Insert email")
                })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.password ?: "",
                onValueChange = viewModel::onPasswordChange, //podia ser igual ao de cima
                placeholder = {
                    Text("Insert password")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.Login(
                        onLoginSuccess = {
                            navController
                                .navigate("all_lists")
                        }
                    )
                }
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            state.error?.let {
                Text(it)
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    ShoppingTheme {
        LoginView()
    }
}