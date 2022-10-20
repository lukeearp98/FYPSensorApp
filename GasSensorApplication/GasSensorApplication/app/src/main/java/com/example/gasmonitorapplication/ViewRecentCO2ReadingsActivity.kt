package com.example.gasmonitorapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_view_recent_data.*
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class ViewRecentCO2ReadingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recent_data)

        retrieveReadings();
        var updateBtn: Button = findViewById(R.id.updateReadingsBtn)

        updateBtn.setOnClickListener {
            retrieveReadings()
        }

    }

    fun retrieveReadings() {
        var updateBtn: Button = findViewById(R.id.updateReadingsBtn)
        updateBtn.setText("Updating...")
        Thread {
            var connection = DatabaseConnection.instance;
            var con: Connection = connection.createConnection()
            val stmt: Statement = con.createStatement()
            val CO2Readings: ResultSet =
                stmt.executeQuery("SELECT TOP 10 ReadingPPM, DateRegistered FROM CO2_Readings ORDER BY DateRegistered DESC");
            val readingList = mutableListOf<CO2Reading>()
            while (CO2Readings.next()) {
                readingList.add(
                    CO2Reading(
                        CO2Readings.getDouble(1).toString(),
                        CO2Readings.getTimestamp(2)
                    )
                );
            }
            connection.closeConnection(con);
            runOnUiThread {
                updateBtn.setText("Update")
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = CO2ReadingAdapter(this, readingList)
            }
        }.start()


    }
}
