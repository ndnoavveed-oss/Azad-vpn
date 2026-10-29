
package com.azadvpn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Server(
    val country: String,
    val flag: String
)

private val servers = listOf(
    Server("United States", "🇺🇸"),
    Server("United Kingdom", "🇬🇧"),
    Server("Singapore", "🇸🇬"),
    Server("Pakistan", "🇵🇰")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AzadVpnScreen()
            }
        }
    }
}

@Composable
private fun AzadVpnScreen() {
    var selected by remember { mutableStateOf(servers[0]) }
    var status by remember { mutableStateOf("Not connected") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                "🛡️ Azad VPN",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(8.dp))
            Text("Free • Simple • Secure")
            Spacer(Modifier.height(24.dp))

            Text(
                "Choose a country",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(servers) { server ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${server.flag}  ${server.country}")
                        Spacer(Modifier.weight(1f))
                        RadioButton(
                            selected = selected == server,
                            onClick = {
                                selected = server
                                status = "Not connected"
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    status = "No VPN server configured"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Connect")
            }

            Spacer(Modifier.height(12.dp))
            Text("Status: $status")
            Spacer(Modifier.height(24.dp))
        }
    }
}
