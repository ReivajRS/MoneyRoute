package com.example.moneyroute.queries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.CustomDatePicker
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun FilterByDateComponent(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    startDate: Long?,
    onStartDateSelected: (Long?) -> Unit,
    endDate: Long?,
    onEndDateSelected: (Long?) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.text_filter_by_date),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange() }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomDatePicker(
                placeholder = stringResource(id = R.string.text_start),
                selectedDate = startDate,
                onDateSelected = { onStartDateSelected(it) },
                modifier = Modifier.width(160.dp),
                enable = checked
            )
            CustomDatePicker(
                placeholder = stringResource(id = R.string.text_end),
                selectedDate = endDate,
                onDateSelected = { onEndDateSelected(it) },
                modifier = Modifier.width(160.dp),
                enable = checked
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FilterByDateComponentPreview() {
    MoneyRouteTheme {
        var checked by remember { mutableStateOf(false) }
        FilterByDateComponent(
            checked = checked,
            onCheckedChange = { checked = !checked },
            startDate = null,
            onStartDateSelected = {  },
            endDate = null,
            onEndDateSelected = {  },
            modifier = Modifier.fillMaxWidth()
        )
    }
}