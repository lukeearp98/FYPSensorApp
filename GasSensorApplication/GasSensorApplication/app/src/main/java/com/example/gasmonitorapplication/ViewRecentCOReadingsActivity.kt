package com.example.gasmonitorapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_view_recent_data.*
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class ViewRecentCOReadingsActivity : AppCompatActivity() {

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
            val COReadings: ResultSet =
                stmt.executeQuery("SELECT TOP 10 ReadingPPM, DateRegistered FROM CO_Readings ORDER BY DateRegistered DESC");
            val readingList = mutableListOf<COReading>()
            while (COReadings.next()) {
                readingList.add(
                    COReading(
                        COReadings.getDouble(1).toString(),
                        COReadings.getTimestamp(2)
                    )
                );
            }
            connection.closeConnection(con);
            runOnUiThread {
                updateBtn.setText("Update")
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = COReadingAdapter(this, readingList)
            }
        }.start()


    }
}
