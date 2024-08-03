package com.ys.speedotransferapp.ui.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.G700

@Composable
fun TransactionScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 32.dp)
    ) {
        Header(text = "Success", navController = navController)
        TransferInfo(
            fromName = "Asmaa Dosuky",
            fromAccount = "Account xxxx7890",
            toName = "Jonathon Smith",
            toAccount = "Account xxxx7890"
        )
    }
}

@Composable
fun TransferInfo(
    fromName: String,
    fromAccount: String,
    toName: String,
    toAccount: String
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(height = 250.dp, width = 500.dp)
    ) {
        TransferCard(
            label = "From",
            name = fromName,
            account = fromAccount,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .size(height = 120.dp, width = 500.dp)
        )
        Image(
            painter = painterResource(R.drawable.small_success),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        )
        TransferCard(
            label = "To",
            name = toName,
            account = toAccount,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .size(height = 120.dp, width = 500.dp)
        )
    }
}

@Composable
fun TransferCard(
    label: String,
    name: String,
    account: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.bank),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                tint = G700
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = label)
                Text(text = name)
                Text(text = account)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TranferInfoPreview() {
    TransactionScreen(rememberNavController())
}