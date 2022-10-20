package com.example.gasmonitorapplication

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.gasmonitorapplication.R.id
import com.example.gasmonitorapplication.R.layout
import com.google.android.material.navigation.NavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import kotlinx.android.synthetic.main.content_main.*
import java.sql.*


class CO2GraphActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.content_main)
        getDBReadings()

        var updateGraph: Button = findViewById(id.updateGraph)

        updateGraph.setOnClickListener{
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }


    }

    fun getDBReadings()
    {
        val graph = findViewById<View>(id.graph) as? GraphView
        var x: Timestamp;
        var y = 0.0;
        var lowestX: Long = 0;
        var highestX: Long = 0;
        var lowestY:Double = 0.0;
        var highestY: Double = 0.0;
        var firstSet: Boolean = false;
        var dateCount = 0;

        var connection = DatabaseConnection.instance;
        var con: Connection = connection.createConnection()
        val stmt: Statement = con.createStatement()
        val deviceDetails: ResultSet = stmt.executeQuery("\n" +
                "SELECT TOP 20 ReadingPPM, DateRegistered FROM (SELECT TOP 20 ReadingPPM, DateRegistered FROM CO2_Readings ORDER BY DateRegistered DESC) CO2_Readings ORDER BY DateRegistered ASC");
        var series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>();
        series.setDrawDataPoints(true);
        while(deviceDetails.next())
        {
            dateCount++;
            if(!firstSet)
            {
                firstSet = true;
                lowestX = deviceDetails.getTimestamp(2).getTime();
                lowestY = deviceDetails.getDouble(1)
            }
            x = deviceDetails.getTimestamp(2)
            y = deviceDetails.getDouble(1)

            if(firstSet)
            {
                if(x.getTime() <= lowestX){
                    lowestX = x.getTime();
                }
                if(y <= lowestY){
                    lowestY = y;
                }
            }

            if(x.getTime() > highestX){
                highestX = x.getTime()
            }
            if(y > highestY){
                highestY = y;
            }
            series.appendData(DataPoint(x,y), false, 50)
        }
        con.close()

        graph?.addSeries(series)
        graph?.getGridLabelRenderer()?.setLabelFormatter(DateAsXAxisLabelFormatter(this));
        graph?.getGridLabelRenderer()?.setNumHorizontalLabels(1); // only 4 because of the space

        graph?.getViewport()?.setMinX(lowestX.toDouble());
        graph?.getViewport()?.setMaxX(highestX.toDouble());
        graph?.getViewport()?.setMinY(lowestY);
        graph?.getViewport()?.setMaxY(highestY);
        graph?.getViewport()?.setXAxisBoundsManual(true);
        graph?.getViewport()?.setYAxisBoundsManual(true);
        graph?.getGridLabelRenderer()?.setHumanRounding(false);

    }
}