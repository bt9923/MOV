package com.template.mov.Photo

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.template.mov.Home.HomeActivity
import com.template.mov.R
import kotlinx.android.synthetic.main.activity_upload_photo.*
import kotlinx.android.synthetic.main.toolbar_mov.*

class UploadPhotoActivity : AppCompatActivity() {

    val SELECT_PHOTO = 1
    val CAPTURE_PHOTO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_photo)

        tv_toolbar_title.text = "Photo"

        continueButtonPhoto.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        continueButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }



        addPhoto.setOnClickListener {
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("Set Your Image")
                .setItems(R.array.upload_photos) { _, which ->
                    when(which){
                        0 -> {
                            val pickerIntent = Intent(ACTION_PICK)
                            pickerIntent.type = "image/*"
                            startActivityForResult(pickerIntent, SELECT_PHOTO)
                        }
                        1 ->{
                            startActivityForResult(Intent(ACTION_IMAGE_CAPTURE), CAPTURE_PHOTO)
                        }
                        2 ->{
                            Toast.makeText(this, " Cancel ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .create().show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PHOTO){
            if (resultCode == Activity.RESULT_OK){
                val imageUri = data?.data
                val inputStream = contentResolver.openInputStream(imageUri!!)
                val selectedImage = BitmapFactory.decodeStream(inputStream)

                imgUploadPhoto.setImageBitmap(selectedImage)

                continueButtonPhoto.visibility = VISIBLE
                line_photo.visibility = VISIBLE
                addPhoto.setImageDrawable(resources.getDrawable(R.drawable.delete_button))
                imgUploadPhoto.setPadding(34,34,34,34)
            }
        }else if (requestCode == CAPTURE_PHOTO){
            if (resultCode == Activity.RESULT_OK){
                onCaptureResult(data)
            }
        }
    }

    private fun onCaptureResult(data: Intent?) {
        val thumbnail = data?.extras?.get("data")

        imgUploadPhoto.maxWidth = 200
        imgUploadPhoto.setImageBitmap(thumbnail as Bitmap)
        imgUploadPhoto.setPadding(12,12,12,12)
        continueButtonPhoto.visibility = VISIBLE
        line_photo  .visibility = VISIBLE
        addPhoto.setImageDrawable(resources.getDrawable(R.drawable.delete_button))
    }
}
