package com.example.helloworld

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Date

@Composable
fun ModifyScreen(context: Context) {
    Column (
        modifier = Modifier.padding(10.dp)
    ) {
        DateInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                ),
            context = context
        )
        PlaceNameInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )
        DescriptionInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        var rate by remember { mutableStateOf(0) }
        RatingInput(modifier = Modifier, rating = rate){ rate = it }

        PhotoInput()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    modifier: Modifier = Modifier,
    context: Context
) {
    var calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        /* context = */ context,
        /* listener = */ { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth.$month.$year"
        },
        /* year = */ year, /* month = */ month, /* dayOfMonth = */ day
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = { date.value = it },
            label = { Text("Date of visit (dd.mm.yyyy)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = { datePickerDialog.show() },
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null,
                modifier = Modifier.padding(5.dp),
                tint = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceNameInput(
    modifier: Modifier = Modifier,
    placeName: String = ""
) {
    var text by remember { mutableStateOf(placeName) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text("Name of the place") },
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionInput(
    modifier: Modifier = Modifier,
    description: String = ""
) {
    var text by remember { mutableStateOf(description) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text("Brief description") },
        maxLines = 10
    )
}

@Composable
fun RatingInput(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    onRatingChange: (Int) -> Unit
) {
    Row {
        for (i in 1..5) {
            Icon(
                modifier = modifier.clickable{onRatingChange(i)},
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = if (i <= rating) {Color.Green} else {Color.Gray}
            )
        }
    }
}

@Composable
fun PhotoInput() {

}

////Check when add button is clicked
////Check if all obligatory input is present
////Check regex for date
//@Composable
//fun inputCheck(): Boolean {
//    var isCorrect = false
//
//    return isCorrect
//}
//
////Transfer data to database if check requirements are fulfilled
//@Composable
//fun InputTransfer() {
//    if(inputCheck()) {
//        //Transfer to database
//    }
//}
