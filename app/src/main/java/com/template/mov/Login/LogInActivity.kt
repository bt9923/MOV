package com.template.mov.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.mov.Photo.UploadPhotoActivity
import com.template.mov.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        signUpButton.setOnClickListener {
            startActivity(Intent(this, signUpActivity::class.java))
        }

        logInButton.setOnClickListener {
            startActivity(Intent(this, UploadPhotoActivity::class.java))
        }
    }
}
