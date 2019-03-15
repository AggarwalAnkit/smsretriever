package com.ankit.self.smsretrieverdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startSmsRetrieval()
        Log.d(MainActivity::class.java.simpleName, "All signatures ${AppSignatureHelper(this).appSignatures}")
    }

    private fun startSmsRetrieval() {
        // Get an instance of SmsRetrieverClient, used to start listening for a matching
        // SMS message.
        val client = SmsRetriever.getClient(this /* context */)

        // Starts SmsRetriever, which waits for ONE matching SMS message until timeout
        // (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
        // action SmsRetriever#SMS_RETRIEVED_ACTION.
        val task = client.startSmsRetriever()

        // Listen for success/failure of the start Task. If in a background thread, this
        // can be made blocking using Tasks.await(task, [timeout]);
        task.addOnSuccessListener {
            Log.d(MainActivity::class.java.simpleName, "SMS Listener started successfully")
        }

        task.addOnFailureListener {
            // Failed to start retriever, inspect Exception for more details
            // ...
            Log.d(MainActivity::class.java.simpleName, "SMS Listener failed to start")
        }
    }
}
