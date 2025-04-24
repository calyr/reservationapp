package com.ucb.ucbtest.dollar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.UUID

@Composable
fun DollarUI(viewModel:DollarViewModel = hiltViewModel()){
    var uuid = UUID.randomUUID().toString()



    val state = viewModel.state.collectAsState()

    Scaffold() {
        padding -> Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Dollar")
            Spacer(modifier = Modifier.height(10.dp))
            when ( val ui = state.value ) {
                is DollarViewModel.StateUI.Success -> {
                   Text("Dollar official:${ui.value}")
                }
                is DollarViewModel.StateUI.Loading -> {
                   Text("Loading")
                }
            }
            Text(uuid)
    }
    }
}