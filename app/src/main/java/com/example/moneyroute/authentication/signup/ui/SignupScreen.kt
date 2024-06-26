package com.example.moneyroute.authentication.signup.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.HeaderImage

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel,
) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Signup(
                modifier = Modifier.align(Alignment.Center),
                viewModel = viewModel
            )
        }
}

@Composable
fun Signup(modifier: Modifier = Modifier, viewModel: SignupViewModel) {
    val firstName: String by viewModel.firstName.collectAsState()
    val lastName: String by viewModel.lastName.collectAsState()
    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val confirmPassword: String by viewModel.confirmPassword.collectAsState()
    val context = LocalContext.current

    Column(modifier = modifier) {
        HeaderImage(
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp),
            painter = painterResource(id = R.drawable.no_bg_logo)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FirstNameField(
            firstName = firstName,
            onTextFieldChange = {
                viewModel.onSignupChange(
                    it,
                    lastName,
                    email,
                    password,
                    confirmPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LastNameField(
            lastName = lastName,
            onTextFieldChange = {
                viewModel.onSignupChange(
                    firstName,
                    it,
                    email,
                    password,
                    confirmPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        EmailField(
            email = email,
            onTextFieldChange = {
                viewModel.onSignupChange(
                    firstName,
                    lastName,
                    it,
                    password,
                    confirmPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordField(
            password = password,
            onTextFieldChange = {
                viewModel.onSignupChange(
                    firstName,
                    lastName,
                    email,
                    it,
                    confirmPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ConfirmPasswordField(
            confirmPassword = confirmPassword,
            onTextFieldChange = {
                viewModel.onSignupChange(
                    firstName,
                    lastName,
                    email,
                    password,
                    it
                )
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        SignupButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(48.dp),
            onSignupClicked = { viewModel.onSignupClicked(context) }
        )
    }
}

@Composable
fun FirstNameField(modifier: Modifier = Modifier, firstName: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = firstName,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(text = stringResource(id = R.string.placeholder_firstname_field)) },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun LastNameField(modifier: Modifier = Modifier, lastName: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = lastName,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(text = stringResource(id = R.string.placeholder_lastname_field)) },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun EmailField(modifier: Modifier = Modifier, email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(text = stringResource(id = R.string.placeholder_email_field)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordField(modifier: Modifier = Modifier, password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(text = stringResource(id = R.string.placeholder_password_field)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ConfirmPasswordField(modifier: Modifier = Modifier, confirmPassword: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = confirmPassword,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(text = stringResource(id = R.string.placeholder_confirm_password_field)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SignupButton(
    modifier: Modifier = Modifier,
    onSignupClicked: () -> Unit
) {
    Button(
        onClick = {
            onSignupClicked()
        },
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.button_signup))
    }
}