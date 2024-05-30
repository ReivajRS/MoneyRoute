package com.example.moneyroute.goals.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.AddButton
import com.example.moneyroute.components.AmountField
import com.example.moneyroute.components.CustomDatePicker
import com.example.moneyroute.components.RowElement
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun RegisterGoalScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterGoalViewModel
) {
    MoneyRouteTheme {
        Scaffold(
            topBar = { RegisterGoalTopBar() }
        ) { innerPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                RegisterGoal(
                    modifier = modifier.padding(innerPadding),
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterGoalTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.title_register_goal),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun RegisterGoal(
    modifier: Modifier = Modifier,
    viewModel: RegisterGoalViewModel
) {
    val goalState: GoalState by viewModel.goalState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        RowElement(text = stringResource(id = R.string.text_label)) {
            LabelField(
                label = goalState.label,
                onLabelChange = { viewModel.onLabelChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_goal_amout)) {
            AmountField(
                amount = goalState.goalAmount,
                onAmountChange = { viewModel.onGoalAmountChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_start_date)) {
            CustomDatePicker(
                selectedDate = goalState.startDate,
                onDateSelected = { viewModel.onStartDateSelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_goal_date)) {
            CustomDatePicker(
                selectedDate = goalState.goalDate,
                onDateSelected = { viewModel.onGoalDateSelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.text_description))
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionField(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            description = goalState.description,
            onDescriptionChange = { viewModel.onDescriptionChange(it) }
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp),
            onAddClicked = { viewModel.onAddClicked(context) }
        )
    }
}

@Composable
fun LabelField(
    modifier: Modifier = Modifier,
    label: String,
    onLabelChange: (String) -> Unit
) {
    TextField(
        value = label,
        onValueChange = { onLabelChange(it) },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    TextField(
        value = description,
        onValueChange = { onDescriptionChange(it) },
        modifier = modifier
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterGoalScreenPreview() {
    MoneyRouteTheme {
        Scaffold(
            topBar = { RegisterGoalTopBar() }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                RegisterGoal(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = RegisterGoalViewModel()
                )
            }
        }
    }
}