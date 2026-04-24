package com.montyblank.mvvmpratico

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.montyblank.mvvmpratico.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttomLogin.setOnClickListener(this)
        setObservers()

    }


    override fun onClick(v: View) {
        if (v.id == R.id.buttom_login) {
            val name = binding.edittextName.text.toString()
            viewModel.doLogin(name)
        }

    }

    private fun setObservers() {
        viewModel.login().observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

}
