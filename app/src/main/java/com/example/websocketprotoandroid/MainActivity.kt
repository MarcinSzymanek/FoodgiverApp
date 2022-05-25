package com.example.websocketprotoandroid
// IMPORTANT!!! code follows https://medium.com/swlh/android-tutorial-part-1-using-java-websocket-with-kotlin-646a5f1f09de


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import androidx.core.view.WindowCompat
import com.example.websocketprotoandroid.databinding.ActivityMainBinding
import org.java_websocket.client.WebSocketClient
import java.net.URI
import java.net.URISyntaxException
import java.nio.ByteBuffer
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception



class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var builder = NotificationCompat.Builder(this, getString(R.string.not_channel_id))
            .setSmallIcon(R.drawable.paw_orange)
            .setContentTitle(getString(R.string.not_channel_name))
            .setContentText(getString(R.string.treat_notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        WebSocketManager.setupWebSocketManager(findNavController(R.id.nav_host_fragment_content_main))
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Hide action bar, it's pointless for now
        supportActionBar?.hide()
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.not_channel_name)
            val descriptionText = getString(R.string.not_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.not_channel_id), name, importance).apply{
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /*override fun onSupportNavigateUp(): Boolean {
        return false
        //val navController = findNavController(R.id.nav_host_fragment_content_main)

                //return navController.navigateUp(appBarConfiguration)
                //super.onSupportNavigateUp()
    }*/


}

