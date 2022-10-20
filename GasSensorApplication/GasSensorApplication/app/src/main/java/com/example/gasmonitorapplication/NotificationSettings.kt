package com.example.gasmonitorapplication

import android.os.StrictMode
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class NotificationSettings
{
    var textNotificationsChecked = false
    var emailNotificationsChecked = false

    companion object {
        val instance = NotificationSettings()
    }
    fun getNotificationSettings(user_name: String?, password: String?)
    {
        var textChecked = 0
        var emailChecked = 0
        var connection = DatabaseConnection.instance;
        var con: Connection = connection.createConnection()
        val stmt: Statement = con.createStatement()
        val deviceDetails: ResultSet = stmt.executeQuery("SELECT * FROM Device_Details WHERE Email='" + user_name +"' AND Password='" + password + "'")
        while (deviceDetails.next()) {
            textChecked = deviceDetails.getInt(4)
            emailChecked = deviceDetails.getInt(5)
        }

        connection.closeConnection(con);
        textNotificationsChecked = textChecked != 0
        emailNotificationsChecked = emailChecked != 0
    }
    fun sendUpdatedSettings(user_name: String?, password: String?, textChecked: Boolean, emailChecked: Boolean)
    {
        var connection = DatabaseConnection.instance;
        var con: Connection = connection.createConnection()
        var textValue = 0;
        var emailValue = 0;
        val stmt: Statement = con.createStatement()
        if(textChecked)
        {
            textValue = 1;
        }
        if(emailChecked)
        {
            emailValue = 1;
        }

        stmt.execute("UPDATE Device_Details SET SendTextNotifications = " + textValue +
                ", SendEmailNotifications = " + emailValue + "WHERE Email='"
                + user_name +"' AND Password='" + password + "'")

        connection.closeConnection(con);

    }
}