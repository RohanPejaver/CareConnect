package com.example.careconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careconnect.R

@Composable
fun IconLoginButton(
    onImageClick : (index : Int) -> Unit,
    onSignUpClick : () -> Unit,
    modifier : Modifier = Modifier
){
    val iconList = listOf(
        R.drawable.google,
        R.drawable.facebook,
        R.drawable.x
    )

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                thickness = 1.dp
            )
            Text("Or login with", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.White)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                thickness = 1.dp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            iconList.forEachIndexed { index, iconResId ->
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Alternative login options",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable {
                            onImageClick(index)
                        }
                        .padding(start = 16.dp)
                )
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}