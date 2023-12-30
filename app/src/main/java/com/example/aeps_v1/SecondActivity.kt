package com.example.aeps_v1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.Nullable


class SecondActivity : AppCompatActivity() {
    val AEPS_REQUEST_CODE = 10923
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AEPS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { //user taps CLOSE button after transaction -- case 1
                val response = data!!.getStringExtra("result")
                //--------- response is transaction data
            } else if (resultCode == RESULT_CANCELED) { // user presses back button
                if (data == null) {
                    //------ If user pressed back without transaction -- case 2
                } else {
                    val response = data.getStringExtra("result")
                    if (response != null && !response.equals("", ignoreCase = true)) {
                        //------ If there is some error in partner parameters, response is that error
                        //------ when user performs the transaction, response is transaction data -- case 1
                    } else {
                    }
                }
            }
        }
    }
}