package com.example.moneyroute.movements.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.movements.MovementList
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import com.example.moneyroute.utilities.FirebaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovementsScreen(
    modifier: Modifier = Modifier,
    firebaseManager: FirebaseManager
) {
    val movementList: List<Movement> by firebaseManager.getMovementsFlow().collectAsState(initial = emptyList())
    MovementsContent(modifier = modifier, movementList)
}

@Composable
fun MovementsContent(
    modifier: Modifier = Modifier,
    movementList: List<Movement>
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_latest_movements),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        MovementList(
            movementList = movementList
        )
        MovementList(
            movementList = listOf(
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
                Movement("", "", "algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            )
        )
    }
}
//
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun MovementsScreenPreview() {
//    MoneyRouteTheme {
//        MovementsScreen(
//
//        )
//    }
//}