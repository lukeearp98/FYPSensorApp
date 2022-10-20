package com.example.gasmonitorapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gasmonitorapplication.R.*

/**
 * A Login Form Example in Kotlin Android
 */
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_welcome)

        val loginMgr = LoginManager.getInstance(this)

        if (loginMgr.isMemberLoggedIn) {
            val intent = Intent(this, NavigationActivity::class.java)
            //Clears the back button
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            this.startActivity(intent)
        }
        // get reference to all views
        var btn_login_page: Button = findViewById(id.btn_login_page)
        var btn_register_page: Button = findViewById(id.btn_register_page)

        // set on-click listener
        btn_login_page.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        btn_register_page.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}