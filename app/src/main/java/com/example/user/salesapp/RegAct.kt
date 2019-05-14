package com.example.user.salesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        reg_signup.setOnClickListener {

            if (reg_password.text.toString().equals(confirm_password.text.toString()) ) {

                var url = "http://${UserInfo.ip}/Sales/add_user.php?mobile=" + reg_mobile.text.toString() +
                        "&password=" + reg_password.text.toString() + "&name=" + reg_name.text.toString() + "&address=" +
                        reg_address.text.toString()

                var rq:RequestQueue=Volley.newRequestQueue(this)
                var sr =StringRequest(Request.Method.GET,url,Response.Listener { response ->
                    if (response.equals("0"))
                        Toast.makeText(this,"Mobile number already used ",Toast.LENGTH_LONG).show()
                    else {
                        Toast.makeText(this, "User successfully created", Toast.LENGTH_LONG).show()
                        UserInfo.mobile=reg_mobile.text.toString()
                        var i=Intent(this,HomeAct::class.java)
                        startActivity(i)
                    }

                },Response.ErrorListener { error ->

                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })
                rq.add(sr)
            }
            else{
                Toast.makeText(this,"Passwords do not Match",Toast.LENGTH_LONG).show()
            }
        }

    }
}
