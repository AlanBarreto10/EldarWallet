package com.example.eldarwallet.ui.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eldarwallet.navigation.graphs.Graph
import com.example.eldarwallet.navigation.screens.AuthScreen
import com.example.eldarwallet.ui.components.BackgroudImage

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
){
    /* Requerimiento: Pantalla Login.
       Extra: registrar nuevo usuario */

    val viewState by loginViewModel.viewState.collectAsState()
    val openDialog by loginViewModel.openDialog.observeAsState(initial = false)

    if (viewState.isLoading) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)

            )
        }
    } else if (viewState.isLogIn) {
        LaunchedEffect(Unit){
            navController.navigate(Graph.HOME) {
                Log.d("LOGIN","entro a home")
            }
        }
    } else {
        if (openDialog) {
            Box(Modifier.fillMaxSize()) {
                BackgroudImage()
                Login(Modifier.align(Alignment.Center), loginViewModel, navController)
                AlertDialog(
                    icon = {
                        Icons.Filled.Info
                    },
                    title = {
                        Text(text = "Atención")
                    },
                    text = {
                        Text(text = " Verifique los datos ingresados")
                    },
                    onDismissRequest = {
                        loginViewModel.setOpenDialog(false)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                loginViewModel.setOpenDialog(false)
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                )
            }

        } else {
            Box(Modifier.fillMaxSize()) {
                BackgroudImage()
                Login(Modifier.align(Alignment.Center), loginViewModel, navController)
                if (!viewState.isValidEmail || !viewState.isValidPassword) {
                    loginViewModel.setOpenDialog(false)
                }
            }
        }
    }
}

@Composable
fun Login(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    Column(modifier = modifier) {
        HeadLogin(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        CardBody(loginViewModel, navController)
    }
}

@Composable
fun HeadLogin(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "EldarWallet",
        fontFamily = FontFamily.Serif,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        color = Color(0xFF1B92A2),
        fontSize = 50.sp)
}

@Composable
private fun CardBody(loginViewModel: LoginViewModel, navController: NavController) {
    Card(modifier = Modifier
        .height(500.dp)
        .padding(top = 16.dp, bottom = 60.dp)
        .padding(horizontal = 30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(modifier = Modifier
            .height(450.dp)
            .padding(10.dp)
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeTitle()
            Spacer(modifier = Modifier.padding(15.dp))
            CardLoggin(loginViewModel, navController)

        }
    }
}

@Composable
fun CardLoggin(loginViewModel: LoginViewModel, navController: NavController) {
    Column(modifier = Modifier
        .height(400.dp)
        .padding(10.dp)
        .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(loginViewModel)
        Spacer(modifier = Modifier.padding(20.dp))
        PasswordField(loginViewModel)
        Spacer(modifier = Modifier.weight(1f))
        LoginButton(loginViewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        CreateAccount(navController)
    }
}

@Composable
fun CreateAccount(navController: NavController) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "No tienes una cuenta ? ",
            fontSize = 15.sp,
            color = Color.Black
        )
        TextButton(onClick = { navController.navigate(AuthScreen.SignUp.route)}) {
            Text(text = "Crear aqui",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontSize = 15.sp,
                color = Color(0xFF1B92A2)
            )
        }


    }
}

@Composable
fun LoginButton(loginViewModel: LoginViewModel) {
    val email:String by loginViewModel.email.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable:Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Button(onClick = { loginViewModel.onLoginSelected(email,password) } , modifier = Modifier
        .fillMaxWidth()
        .height(58.dp)
        .padding(horizontal = 5.dp), colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF00BCD4), // Cambia el color según tus preferencias
    ), enabled = isLoginEnable) {
        Text(text = "Log in",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

    }
}

@Composable
fun PasswordField(loginViewModel: LoginViewModel) {
    val email:String by loginViewModel.email.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "")
    var hidden by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = password,
        onValueChange = { loginViewModel.onLoginChanged(email, password = it) },
        label = { Text("Password", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1B92A2),
            focusedIndicatorColor = Color(0xFF1B92A2),
            unfocusedIndicatorColor = Color(0xFF1B92A2)
        ),
        trailingIcon = {
            val imagen = if (hidden){
                Icons.Filled.VisibilityOff
            }else{ Icons.Filled.Visibility }
            IconButton(onClick = { hidden = !hidden }) {
                Icon(imageVector = imagen, contentDescription = "show password")
            }
        },
        visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
private fun EmailField(loginViewModel: LoginViewModel) {
    val email:String by loginViewModel.email.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "")

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = email,
        onValueChange = { loginViewModel.onLoginChanged(email= it, password) },
        label = { Text("Email", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1B92A2),
            focusedIndicatorColor = Color(0xFF1B92A2),
            unfocusedIndicatorColor = Color(0xFF1B92A2)
        )
    )
}

@Composable
fun WelcomeTitle() {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            buildAnnotatedString {
                withStyle(style =  MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                ) {
                    append("Bienvenido a ")
                }
                withStyle(style = MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1B92A2)
                )
                ) {
                    append("EldarWallet")
                }
            }
        )
    }
}