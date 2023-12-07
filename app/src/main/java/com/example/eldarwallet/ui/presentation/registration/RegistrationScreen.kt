package com.example.eldarwallet.ui.presentation.registration

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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.eldarwallet.ui.components.BackgroudImage


@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    navController: NavController
) {
    val viewState by registrationViewModel.viewState.collectAsState()

    if (viewState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)

            )
        }
    } else if (viewState.isRegister){
        LaunchedEffect(Unit){
            navController.navigate(Graph.HOME) {}
        }
    }else{
        Box(Modifier.fillMaxSize()) {
            BackgroudImage()
            RegisterBody(Modifier.align(Alignment.Center), registrationViewModel)
        }
    }

}

@Composable
fun RegisterBody(
    modifier: Modifier,
    registrationViewModel: RegistrationViewModel
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(5.dp))
        Head(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        CardBody(registrationViewModel)
    }
}


@Composable
fun Head(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "EldarWallet",
        fontWeight = FontWeight.Bold,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        color = Color(0xFF1B92A2),
        fontSize = 40.sp
    )
}


@Composable
private fun CardBody(registrationViewModel: RegistrationViewModel) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, bottom = 60.dp)
        .padding(horizontal = 30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterTitle()
            Spacer(modifier = Modifier.padding(12.dp))
            CardRegister(registrationViewModel)
            Spacer(modifier = Modifier.weight(1f))
            SignUpButton(registrationViewModel)
            Spacer(modifier = Modifier.padding(12.dp))
        }
    }
}

@Composable
fun RegisterTitle() {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            buildAnnotatedString {
                withStyle(style =  MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                ) {
                    append("Crea tu cuenta en ")
                }
                withStyle(style =  MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color =  Color(0xFF1B92A2)
                )
                ) {
                    append("EldarWallet")
                }
            }
        )
    }
}

@Composable
fun CardRegister(registrationViewModel: RegistrationViewModel) {
    Column(modifier = Modifier
        .height(400.dp)
        .padding(10.dp)
        .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmailField(registrationViewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        FirstNameField(registrationViewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        SecondNameField(registrationViewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        Password(registrationViewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        RepeatPassword(registrationViewModel)
    }
}

@Composable
fun SignUpButton(registrationViewModel: RegistrationViewModel) {
    val email:String by registrationViewModel.email.observeAsState(initial = "")
    val firstname:String by registrationViewModel.firstname.observeAsState(initial = "")
    val secondname:String by registrationViewModel.secondname.observeAsState(initial = "")
    val password:String by registrationViewModel.password.observeAsState(initial = "")


    Button(onClick = { registrationViewModel.onSignInSelected(email, firstname, secondname, password) } ,
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(horizontal = 5.dp), colors = ButtonDefaults.buttonColors(
            containerColor =  Color(0xFF00BCD4),
            disabledContentColor = Color(0xFFCCCCCC)
        )) {
        Text(text = "Sign up",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp) //formato NUNITO
    }

}

@Composable
private fun EmailField(registrationViewModel: RegistrationViewModel) {
    val email:String by registrationViewModel.email.observeAsState(initial = "")

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = email,
        onValueChange = { registrationViewModel.onEmailChanged(email = it) },
        label = { Text("Email", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//2
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color(0xFF1B92A2),
            focusedIndicatorColor =  Color(0xFF1B92A2),
            unfocusedIndicatorColor =  Color(0xFF1B92A2)
        )
    )




}

@Composable
private fun FirstNameField(registrationViewModel: RegistrationViewModel) {
    val firstname:String by registrationViewModel.firstname.observeAsState(initial = "")

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = firstname,
        onValueChange = { registrationViewModel.onFirstNameChanged(firstname = it) },
        label = { Text("Nombre", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//2
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color(0xFF1B92A2),
            focusedIndicatorColor =  Color(0xFF1B92A2),
            unfocusedIndicatorColor =  Color(0xFF1B92A2)
        )
    )

}

@Composable
private fun SecondNameField(registrationViewModel: RegistrationViewModel) {
    val secondname:String by registrationViewModel.secondname.observeAsState(initial = "")

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = secondname,
        onValueChange = { registrationViewModel.onSecondNameChanged(secondname = it) },
        label = { Text("Apellido", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//2
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color(0xFF1B92A2),
            focusedIndicatorColor =  Color(0xFF1B92A2),
            unfocusedIndicatorColor = Color(0xFF1B92A2)
        )
    )

}

@Composable
private fun Password(registrationViewModel: RegistrationViewModel) {
    val password:String by registrationViewModel.password.observeAsState(initial = "")
    var hidden by remember { mutableStateOf(true) } //1

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = password,
        onValueChange = { registrationViewModel.onPasswordChanged(password = it) },
        label = { Text("Contraseña", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color(0xFF1B92A2),
            focusedIndicatorColor =  Color(0xFF1B92A2),
            unfocusedIndicatorColor =  Color(0xFF1B92A2)
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
private fun RepeatPassword(registrationViewModel: RegistrationViewModel) {
    val repeatpassword:String by registrationViewModel.repeatpassword.observeAsState(initial = "")
    var hidden by remember { mutableStateOf(true) }

    TextField(
        modifier = Modifier.padding(horizontal = 40.dp),
        value = repeatpassword,
        onValueChange = { registrationViewModel.onRepeatPasswordChanged(repeatpassword = it) },
        label = { Text("Repetir contraseña", color =  Color(0xFF1B92A2)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color(0xFF1B92A2),
            focusedIndicatorColor =  Color(0xFF1B92A2),
            unfocusedIndicatorColor =  Color(0xFF1B92A2)
        ),
        trailingIcon = {// 4
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
