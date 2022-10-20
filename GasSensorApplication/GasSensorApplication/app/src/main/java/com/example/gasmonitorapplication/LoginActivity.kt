package com.example.gasmonitorapplication

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import com.example.gasmonitorapplication.R.id
import com.example.gasmonitorapplication.R.layout
import net.sourceforge.jtds.jdbc.*;

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)

        // get reference to all views
        var et_login_email: EditText = findViewById(id.et_login_email)
        var et_password: EditText = findViewById(id.et_password)
        var btn_reset: Button = findViewById(id.btn_reset)
        var btn_submit: Button = findViewById(id.btn_submit)

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_login_email.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            var user_name = et_login_email.text;
            var password = et_password.text;
            var member = MemberDTO(0, "", "", "");
            if (user_name.isEmpty() || password.isEmpty())
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
            val deviceDetails: ResultSet = stmt.executeQuery("SELECT * FROM Device_Details WHERE Email='" + user_name.toString() +"' AND Password='" + password.toString() + "'");
            if(!deviceDetails.next())
            {
                Toast.makeText(
                    getApplicationContext(),
                    "Username or password is incorrect.",
                    Toast.LENGTH_LONG).show();
                con.close();
                return@setOnClickListener;
            }
            else
            {

                    member = MemberDTO(
                        deviceDetails.getInt(1),
                        deviceDetails.getString(2),
                        deviceDetails.getString(2),
                        deviceDetails.getString(3)
                    )

                LoginManager.getInstance(this@LoginActivity).loginMember(member)
                connection.closeConnection(con);
                var intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                finish()
                this@LoginActivity.startActivity(intent)
            }
        }
    }
}