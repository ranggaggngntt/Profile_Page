package com.example.nohoax

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = intent.extras
        val nameUser = intent?.getString("name")

        edit_profile.setText(nameUser)

        editProfile.setOnClickListener{
            saveData()
        }
    }

    private fun saveData() {
        val nameEdit = edit_profile.text.toString()

        if (!nameEdit.isEmpty()) {
            val result = Intent()

            result.putExtra("name", nameEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
