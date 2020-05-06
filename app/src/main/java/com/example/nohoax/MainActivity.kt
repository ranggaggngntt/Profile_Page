package com.example.nohoax

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nohoax.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var InputNama : String = ""
    private var InputEmail : String = ""
    private var InputAlamat : String = ""
    private var InputTelepon : String = ""
    private var InputJk : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataspinnerJenisKelamin()

        kirim_data.setOnClickListener { validasiInput() }
    }

    private fun setDataspinnerJenisKelamin(){
        val adapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerJenisKelamin.adapter = adapter
    }

    private fun validasiInput(){

        InputNama = edit_nama.text.toString()
        InputEmail = edit_email.text.toString()
        InputAlamat = edit_alamat.text.toString()
        InputTelepon = edit_telepon.text.toString()
        InputJk = spinnerJenisKelamin.selectedItem.toString()

        when{
            InputNama.isEmpty() -> edit_nama.error = "Nama Harus Diisi!"
            InputEmail.isEmpty() -> edit_email.error = "Email Harus Diisi!"
            InputAlamat.isEmpty() -> edit_alamat.error = "Alamat Harus Diisi!"
            InputTelepon.isEmpty() -> edit_telepon.error = "Telepon Harus Diisi!"
            InputJk.equals("Pilih Jenis Kelamin", ignoreCase = true) -> DataToast("Harus Memilih Jenis Kelamin")

            else -> {
                DataToast("Profile Page")
                goToProfileAcitivity()
            }
        }
    }

    private fun DataToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToProfileAcitivity(){
        val intent = Intent(this, ProfileActivity::class.java)

        val bundle = Bundle()

        bundle.putString("Nama", InputNama)
        bundle.putString("Jk", InputJk)
        bundle.putString("Email", InputEmail)
        bundle.putString("Alamat", InputAlamat)
        bundle.putString("Telepon", InputTelepon)

        intent.putExtras(bundle)

        startActivity(intent)
    }


}
