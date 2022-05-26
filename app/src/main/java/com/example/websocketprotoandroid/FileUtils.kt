package com.example.websocketprotoandroid

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun readFromFile(context : Context, filename : String) : String{
    var returnVal : String = ""
    try{
        val inputStreamReader : InputStreamReader = InputStreamReader(context.openFileInput(filename))
        val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)
        var receiveString : String? = ""
        val stringBuilder : StringBuilder = StringBuilder()
        var done = false
        while(!done){
            receiveString = bufferedReader.readLine()
            if(receiveString == null){
                done = true
            }
            else{
                stringBuilder.append("").append(receiveString)
            }
        }
        inputStreamReader.close()
        returnVal = stringBuilder.toString().trim()
    }
    catch(ex : FileNotFoundException){
        Log.e("File read", "Cannot read file")
    }
    return returnVal
}

fun writeToFile(filename : String, data : String, context : Context){
    val outputStreamWriter : OutputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
    outputStreamWriter.write(data)
    outputStreamWriter.close()
}