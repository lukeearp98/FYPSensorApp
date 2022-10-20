package com.example.gasmonitorapplication

import android.os.StrictMode
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DatabaseConnection {
    companion object {
        val instance = DatabaseConnection()
    }
    fun createConnection(): Connection
    {
        var driver = "net.sourceforge.jtds.jdbc.Driver"
        Class.forName(driver)
        var connectionUrl = "jdbc:jtds:sqlserver://e013988g.database.windows.net/learpfyp;encrypt=false;user=e013988g;password=lukefyp2020!;instance=SQLEXPRESS"
        var user = "e013988g"
        var pass = "lukefyp2020!"
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var con: Connection = DriverManager.getConnection(connectionUrl, user, pass)
        return con;
    }
    fun closeConnection(con: Connection)
    {
        con.close();
    }
}