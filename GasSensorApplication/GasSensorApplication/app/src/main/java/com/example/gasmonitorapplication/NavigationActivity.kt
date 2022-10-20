package com.example.gasmonitorapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        var viewCO2Graph: Button = findViewById(R.id.viewCO2GraphBtn)
        var viewCOGraph: Button = findViewById(R.id.viewCOGraph)
        var changeSettings: Button = findViewById(R.id.changeSettingsBtn)
        var logoutBtn: Button = findViewById(R.id.logoutBtn)
        var viewRecentCO2Btn: Button = findViewById(R.id.viewRecentCO2Data)
        var viewRecentCOBtn: Button = findViewById(R.id.viewRecentCOData)

        viewCO2Graph.setOnClickListener {
            val intent = Intent(this, CO2GraphActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
        viewCOGraph.setOnClickListener {
            val intent = Intent(this, COGraphActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
        changeSettings.setOnClickListener {
            val intent = Intent(this, ChangeSettingsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
        logoutBtn.setOnClickListener {
            var loginMgr = LoginManager.getInstance(this)
            loginMgr.logout();

            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            this.startActivity(intent)
        }
        viewRecentCO2Btn.setOnClickListener {
            val intent = Intent(this, ViewRecentCO2ReadingsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
        viewRecentCOBtn.setOnClickListener {
            val intent = Intent(this, ViewRecentCOReadingsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
    }
}
