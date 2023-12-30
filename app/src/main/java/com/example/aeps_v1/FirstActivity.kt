package com.example.aeps_v1

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.aeps_v1.databinding.ActivityFirstBinding
import `in`.co.eko.ekopay.EkoPayActivity

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    val AEPS_REQUEST_CODE = 10923
    //to be inatilized
    private lateinit var secret_key_timestamp:String
    private lateinit var secret_key:String
    private lateinit var callback_url:String
    private lateinit var initiator_logo_url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, EkoPayActivity::class.java)
        val bundle = Bundle()
        //Initialize all unknown variables and replace all dummy values
        secret_key_timestamp="1"
        secret_key="1"
        callback_url="1"
        initiator_logo_url="1"
        bundle.putString("environment", "uat")
        bundle.putString("product", "aeps")
        bundle.putString("secret_key_timestamp", secret_key_timestamp)
        bundle.putString("secret_key", secret_key)
        bundle.putString("developer_key", "becbbce45f79c6f5109f848acd540567")
        bundle.putString("initiator_id", "9962981729")
        bundle.putString("callback_url", callback_url)
//        bundle.putString("callback_url_custom_headers", callback_url_custom_headers) //optional
//        bundle.putString("callback_url_custom_params", callback_url_custom_params) //optional
        bundle.putString("user_code", "20810200")
        bundle.putString("initiator_logo_url", initiator_logo_url)
        bundle.putString("partner_name", "PARTNER Name INC")
        bundle.putString("language", "en")
        intent.putExtras(bundle)

        binding.btnAepsGateway.setOnClickListener {
            startActivityForResult(intent, AEPS_REQUEST_CODE)
        }


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
                    binding.tvResult.text = "Null value, no transaction"
                } else {
                    val response = data.getStringExtra("result")
                    if (response != null && !response.equals("", ignoreCase = true)) {
                        //------ If there is some error in partner parameters, response is that error
                        //------ when user performs the transaction, response is transaction data -- case 1
                        binding.tvResult.text = "some value, correct or wrong"
                    } else {
                    }
                }
            }
        }
    }


}