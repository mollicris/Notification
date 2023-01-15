package com.example.notificationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private val channelId = "com.example.notificationdemo.chnnel1"
    private var notificationManager : NotificationManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChanner(channelId,"DemoChannel","this is a demo")
        val button = findViewById<Button>(R.id.btnMessage)
        button.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        //val tapResultIntent = Intent(this,SecondActivity::class.java)

        val notification = NotificationCompat.Builder(this@MainActivity,channelId)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChanner(id : String, name :String, channelDescription : String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            //Estas 2 filas se reemplazan por el apply
            //val channel = NotificationChannel(id,name,importance)
            //channel.description = channelDescription

            val channel = NotificationChannel(id,name,importance).apply {
                description =channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}