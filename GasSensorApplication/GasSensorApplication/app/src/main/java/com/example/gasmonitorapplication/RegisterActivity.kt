package com.example.gasmonitorapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gasmonitorapplication.R.*
import java.sql.Connection
import java.sql.Statement

/**
 * A Login Form Example in Kotlin Android
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_register)
        // get reference to all views
        var et_fullname: EditText = findViewById(id.et_fullname)
        var et_email: EditText = findViewById(id.et_email)
        var et_register_password: EditText = findViewById(id.et_register_password)
        var btn_register_device: Button = findViewById(id.btn_register_device)

        // set on-click listener
        btn_register_device.setOnClickListener {
            var member = MemberDTO(0, "", "", "");
            val et_fullname = et_fullname.text
            val et_email = et_email.text
            val et_register_password = et_register_password.text
            if (et_email.isEmpty() || et_register_password.isEmpty())
            {
                Toast.makeText(
                    getApplicationContext(),
                    "Username or password is empty.",
                    Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }
            var connection = DatabaseConnection.instance;
            var con: Connection = connection.createConnection()
            val stmt: Statement = con.createStatement()

            member = MemberDTO(0,
                et_email.toString(),
                et_email.toString(),
                et_register_password.toString()
            )

            stmt.execute("INSERT INTO Device_Details (Email, Password, SendTextNotifications, SendEmailNotifications) VALUES ('" + et_email.toString() + "', '" + et_register_password.toString() + "', 1, 1)");
            LoginManager.getInstance(this@RegisterActivity).loginMember(member)
            connection.closeConnection(con);
            var intent = Intent(this@RegisterActivity, NavigationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            this@RegisterActivity.startActivity(intent)


        }
    }
}