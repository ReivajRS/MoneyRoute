package com.example.moneyroute.login.ui

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.TitleTopBar
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {
    Scaffold(
        topBar = {
            TitleTopBar(title = stringResource(R.string.title_login), backButton = false)
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Login(
                modifier = Modifier.align(Alignment.Center),
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val loginEnable: Boolean by viewModel.loginEnable.collectAsState()
    val isLoading: Boolean by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    Column(modifier = modifier) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email = email, onTextFieldChange = { viewModel.onLoginChange(it, password) })
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordField(password = password, onTextFieldChange =  { viewModel.onLoginChange(email, it) })
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(48.dp),
            loginEnable = loginEnable,
            onLoginClicked = { viewModel.onLoginClicked(context) }
        )
        HorizontalDivider(modifier = modifier.padding(top = 16.dp, bottom = 8.dp))
        WantToRegister(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.no_bg_logo),
        contentDescription = "Application logo",
        modifier = modifier.size(240.dp)
    )
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
fun PasswordField(modifier: Modifier = Modifier, password: String, onTextFieldChange: (String) -> Unit) {
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
fun LoginButton(modifier: Modifier = Modifier, loginEnable: Boolean, onLoginClicked: () -> Unit) {
    Button(
        onClick = {
            onLoginClicked()
        },
        modifier = modifier,
        enabled = loginEnable
    ) {
        Text(text = stringResource(id = R.string.button_login))
    }
}

@Composable
fun WantToRegister(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.text_you_do_not_have_an_account))
        Text(
            text = stringResource(id = R.string.text_clickable_register_here),
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.clickable { }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview(modifier: Modifier = Modifier) {
    MoneyRouteTheme {
       LoginScreen(viewModel = LoginViewModel())
    }
}