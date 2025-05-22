package ipca.aulas.shopping.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class LoginState(
    val email: String? = null,
    val password: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(newValue: String) {
        state = state.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        state = state.copy(password = newValue)
    }

    fun Login(onLoginSuccess: () -> Unit) {

        if (state.email.isNullOrEmpty()) {
            state = state.copy(error = "Email is required")
            return
        }
        if (state.password.isNullOrEmpty()) {
            state = state.copy(error = "Password is required")
            return
        }
        state = state.copy(isLoading = true)
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(state.email!!, state.password!!)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    onLoginSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    state = state.copy(error = task.exception?.message)
                }
            }
    }
}

