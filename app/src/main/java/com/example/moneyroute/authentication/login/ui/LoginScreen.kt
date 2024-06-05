package com.example.moneyroute.authentication.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.HeaderImage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginClicked: (Boolean) -> Unit,
    onWantToRegisterClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Login(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel,
            onLoginClicked = { onLoginClicked(it) },
            onWantToRegisterClicked = onWantToRegisterClicked
        )
    }
}

@Composable
fun Login(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginClicked: (Boolean) -> Unit,
    onWantToRegisterClicked: () -> Unit
) {
    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val isLoading: Boolean by viewModel.isLoading.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    Column(modifier = modifier) {
        HeaderImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(240.dp),
            painter = painterResource(id = R.drawable.no_bg_logo)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email = email, onTextFieldChange = { viewModel.onLoginChange(it, password) })
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordField(
            password = password,
            onTextFieldChange = { viewModel.onLoginChange(email, it) })
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(48.dp),
            onLoginClicked = {
                scope.launch {
                    val success = viewModel.onLoginClicked(context)
                    onLoginClicked(success)
                }
            }
        )
        HorizontalDivider(modifier = modifier.padding(top = 16.dp, bottom = 8.dp))
        WantToRegister(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onWantToRegisterClicked = onWantToRegisterClicked
        )
    }
}

@Composable
fun EmailField(modifier: Modifier = Modifier, email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = stringResource(id = R.string.placeholder_email_field))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
    )
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    password: String,
    onTextFieldChange: (String) -> Unit
) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = stringResource(id = R.string.placeholder_password_field))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
) {
    Button(
        onClick = {
            onLoginClicked()
        },
        modifier = modifier,
    ) {
        Text(text = stringResource(id = R.string.button_login))
    }
}

@Composable
fun WantToRegister(
    modifier: Modifier = Modifier,
    onWantToRegisterClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.text_you_do_not_have_an_account))
        Text(
            text = stringResource(id = R.string.text_clickable_register_here),
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.clickable {
                onWantToRegisterClicked()
            }
        )
    }
}