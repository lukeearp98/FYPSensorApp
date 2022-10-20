package com.example.gasmonitorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class ChangeSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_settings)
        val notificationSettings = NotificationSettings.instance;
        var textNotfCheckBox: CheckBox = findViewById(R.id.textNtfSetting);
        var emailNotfCheckBox: CheckBox = findViewById(R.id.emailNtfSetting);
        val loggedInMember = LoginManager.getInstance(this).getLoggedInMember();
        var updateBtn: Button = findViewById(R.id.updateBtn)
        updateBtn.setText("Retrieving Settings...")
        Thread{
            notificationSettings.getNotificationSettings(loggedInMember.username, loggedInMember.password);
            runOnUiThread{
                updateBtn.setText("Update")
                textNotfCheckBox.setChecked(notificationSettings.textNotificationsChecked);
                emailNotfCheckBox.setChecked(notificationSettings.emailNotificationsChecked);
            }
        }.start()


        updateBtn.setOnClickListener{
            Thread{
                notificationSettings.sendUpdatedSettings(loggedInMember.username, loggedInMember.password,
                    textNotfCheckBox.isChecked, emailNotfCheckBox.isChecked)
                runOnUiThread{
                    Toast.makeText(this, "Settings Updated!", Toast.LENGTH_SHORT).show()
                }
            }.start()

        }
    }
}
