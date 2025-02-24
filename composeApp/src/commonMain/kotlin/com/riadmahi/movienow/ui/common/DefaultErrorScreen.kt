package com.riadmahi.movienow.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.button_retry
import movienow.composeapp.generated.resources.error_internal
import movienow.composeapp.generated.resources.error_internal_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun DefaultErrorScreen(
    showRetry : Boolean = false,
    retryClicked: () -> Unit = { }
) {
    Column (
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorMessage(
            title = stringResource(Res.string.error_internal),
            description = stringResource(Res.string.error_internal_description)
        )
        if(showRetry){
            Spacer(Modifier.height(50.dp))
            MovieNowButton(text = stringResource(Res.string.button_retry)) {
                retryClicked()
            }
        }
    }
}