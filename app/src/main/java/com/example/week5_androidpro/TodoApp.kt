package com.example.week5_androidpro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun TodoApp() {
    var taskList by remember { mutableStateOf(listOf<TodoTask>()) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text("Введите задачу") }
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (textState.text.isNotEmpty()) {
                    taskList = taskList + TodoTask(textState.text)
                    textState = TextFieldValue("")
                }
            }) {
                Text("Добавить")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(taskList) { task ->
                TodoItem(
                    task = task,
                    onCheckedChange = { checked ->
                        taskList = taskList.map {
                            if (it == task) it.copy(isDone = checked) else it
                        }
                    },
                    onDelete = {
                        taskList = taskList - task
                    }
                )
            }
        }
    }
}
