package com.example.nohoax

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        val REQUEST_CODE = 100

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ambilData()

        edit.setOnClickListener{navigationProfile()}

        telp.setOnClickListener{
            dialPhoneNumber(telepon.text.toString())
    }

    }

    private fun ambilData() {
        val bundle = intent.extras

        val nama = bundle?.getString("Nama")
        val jk = bundle?.getString("Jk")
        val Email = bundle?.getString("Email")
        val Telepon = bundle?.getString("Telepon")
        val Alamat = bundle?.getString("Alamat")

        name.text = nama
        jenisKelamin.text = jk
        email.text = Email
        telepon.text = Telepon
        alamat.text = Alamat
    }

    private fun navigationProfile() {
        val intent = Intent(this, EditActivity::class.java)
        val nameUser = name.text.toString()
        intent.putExtra("name", nameUser)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
        if (resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("name")
            name.text = result
        } else {

            Toast.makeText(this, "Gagal Edit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent (Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}