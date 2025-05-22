package ipca.aulas.shopping

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.aulas.shopping.ui.theme.ShoppingTheme
import ipca.aulas.shopping.ui.login.LoginView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.aulas.shopping.ui.listItems.AddListView
import ipca.aulas.shopping.ui.listItems.AllListView
import ipca.aulas.shopping.ui.listItems.items.AddItemView
import ipca.aulas.shopping.ui.listItems.items.ItemsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()
            ShoppingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("items/{listId}") {
                            val listId = it.arguments?.getString("listId")
                            ItemsView(
                                listId = listId!!,
                                navController = navController
                            )
                            Log.d("MainActivity", "Navigating to ItemsView with listId: $listId")
                        }
                        composable("add_item/{listId}") {
                            val listId = it.arguments?.getString("listId")
                            AddItemView(
                                listId = listId!!,
                                navController = navController
                            )
                            Log.d("MainActivity", "Navigating to AddItemView with listId: $listId")
                        }
                        composable("all_lists") {
                            AllListView(navController = navController)
                        }
                        composable("add_list") {
                            AddListView(navController = navController)
                        }
                        composable("login") {
                            LoginView(navController = navController)
                        }
                    }
                }
            }
            LaunchedEffect(key1 = true) {
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    navController.navigate("all_lists")
                }
            }
        }
    }
}