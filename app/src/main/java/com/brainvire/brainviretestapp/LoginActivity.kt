package com.brainvire.brainviretestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.brainvire.brainviretestapp.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

      //login process
      btnLogin.setOnClickListener {

        try {
            if(etUserName.text.isNullOrEmpty())
            {
                tilUserName.error = "Enter User Name"
                tilPassword.error = null
            }
            else if(etPassword.text.isNullOrEmpty())
            {
                tilPassword.error = "Enter Password"
                tilUserName.error = null
            }
            else if(etPassword.text.toString() == getString(R.string.password_string) && etUserName.text.toString() == getString(R.string.username_string)){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
            else{
                tilPassword.error = null
                tilUserName.error = null
                Toast.makeText(this,"Incorrect User Name or Password!",Toast.LENGTH_SHORT).show()
            }
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }

      }
    }

    override fun onResume() {
        super.onResume()
        etUserName.setText("")
        etPassword.setText("")
    }

}