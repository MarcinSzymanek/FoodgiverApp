package com.example.websocketprotoandroid

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.websocketprotoandroid.databinding.FragmentAuthBinding
import com.example.websocketprotoandroid.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Auth", "VIEW CREATED")
        val editText = binding.editTextAuth
        var authstring : String? = null
        binding.buttonAuth.setOnClickListener {
            Log.w("Auth", "Button pressed")
            authstring = editText?.text.toString()
            val act = activity
            if(authstring != null && authstring != ""){
                writeToFile("config.txt", authstring!!, act!!.applicationContext )
                writeToFile("auth.txt", "true", act!!.applicationContext)
                Toast.makeText( context,"Your device number has been saved!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_to_connect_screen)
                Log.i("Auth", "Saved message: ")
                Log.i("Auth", authstring.toString().trim())
            }

            Log.w("Auth", authstring.toString())

        }
    }
}