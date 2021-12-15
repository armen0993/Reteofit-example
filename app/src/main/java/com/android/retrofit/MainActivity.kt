package com.android.retrofit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.entity.UserSignInRequestModel
import com.android.retrofit.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModel()

        viewModel.responseLiveData.observe(this,{
            binding.text.text = it.accessToken
        })

        viewModel.responseDetailsLiveData.observe(this,{
            binding.text.text = it.firstName
        })

        val pref  = getSharedPreferences("sharedKey",Context.MODE_PRIVATE)


        binding.button.setOnClickListener {

            viewModel.responseModel( UserSignInRequestModel(password = binding.password.text.toString(), username = binding.phoneNumber.text.toString()))

            val savedToken = pref.getString("currentKey","")
            if (!savedToken.isNullOrEmpty()){
                Toast.makeText(this,"Token is not empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonClear.setOnClickListener {
            pref.edit().putString("currentKey","").apply()
        }
        binding.buttonGetDetails.setOnClickListener {
            viewModel.responseDetailsModel()

        }

    }
}