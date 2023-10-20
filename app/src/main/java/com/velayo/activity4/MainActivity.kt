package com.velayo.activity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.velayo.activity4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        //load state of Ui elements from SharedPreferences
        binding.emailEditText.setText(sharedPreferences.getString("email", ""))
        binding.nicknameEditText.setText(sharedPreferences.getString("nickname", ""))
        binding.notificationCheckBox.isChecked = sharedPreferences.getBoolean("allowNotif", false)

        val selectedTheme = sharedPreferences.getInt("selectedTheme", -1)
        if (selectedTheme != -1) {
            binding.themeOption.check(selectedTheme)
        }

        binding.saveButton.setOnClickListener{
            //Save the state of UI elements to SharePreferences
            val input = sharedPreferences.edit()
            input.putString("email", binding.emailEditText.text.toString())
            input.putString("nickname", binding.nicknameEditText.text.toString())
            input.putBoolean("allowNotif", binding.notificationCheckBox.isChecked)
            input.putInt("selectedTheme", binding.themeOption.checkedRadioButtonId)
            input.apply()

            Toast.makeText(this, "Saved Settings!", Toast.LENGTH_LONG).show()
        }
    }
}