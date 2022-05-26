package com.example.websocketprotoandroid

import android.app.*
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.websocketprotoandroid.databinding.FragmentFirstBinding

private const val TAG = "FirstFragment"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var notificationManager : NotificationManager? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val mAct = activity
        if(mAct != null){
            notificationManager = ContextCompat.getSystemService(mAct, NotificationManager::class.java) as NotificationManager
        }
        else{
            Log.d("FAct", "mAct is null...")
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "View has been created. Listening to U input")
        var act : Activity? = activity
        val id = act?.let { readFromFile(it.applicationContext, "config.txt") }
        WebSocketManager.setupWebSocketManager(findNavController(), act!!, id!!)

        binding.buttonConnect.setOnClickListener {
            WebSocketManager.connectClient()
            if(WebSocketManager.connected){
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            else{
                Log.i(TAG, "Could not connect to server")
                Toast.makeText( context,"Could not connect to the server! Try again later.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}