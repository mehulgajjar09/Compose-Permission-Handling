package com.example.composepermissionhandling

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    iPermissionTextProvider: IPermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant permission"
                    } else {
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingsClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp),
                )
            }
        },
        title = {
            Text(text = "Permission required")
        },
        text = {
            Text(text = iPermissionTextProvider.getDescription(isPermanentDeclined = isPermanentlyDeclined))
        },
        modifier = modifier
    )
}

interface IPermissionTextProvider {
    fun getDescription(isPermanentDeclined: Boolean): String
}

class CameraPermissionTextProvider : IPermissionTextProvider {
    override fun getDescription(isPermanentDeclined: Boolean): String {
        return if (isPermanentDeclined) {
            "It seems you permanently declined camera permission. " +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs access to your camera so that your friends can see you in a call."
        }
    }
}

class RecordAudioPermissionTextProvider : IPermissionTextProvider {
    override fun getDescription(isPermanentDeclined: Boolean): String {
        return if (isPermanentDeclined) {
            "It seems you permanently declined microphone permission. " +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs access to your microphone so that your friends can hear you in a call."
        }
    }
}

class PhoneCallPermissionTextProvider : IPermissionTextProvider {
    override fun getDescription(isPermanentDeclined: Boolean): String {
        return if (isPermanentDeclined) {
            "It seems you permanently declined calling permission. " +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs phone calling permission so that you can talk to your friends."
        }
    }
}