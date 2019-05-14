@file:Suppress("DEPRECATION")

package com.example.user.salesapp


import android.os.Bundle
//import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.DialogFragment
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
class QtyFragment :DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         var v=inflater.inflate(R.layout.fragment_qty, container, false)
        var et=v.findViewById<EditText>(R.id.et_qty)
        var btn=v.findViewById<Button>(R.id.btn_qty)
        btn.setOnClickListener {
            var  url="http://${UserInfo.ip}/Sales/add_temp.php?mobile=${UserInfo.mobile}&itemid=${UserInfo.itemId}&qty=${et.text.toString()}"
            var rq: RequestQueue = Volley.newRequestQueue(activity)
            var sr = StringRequest(Request.Method.GET,url, Response.Listener { response ->
                var i= Intent(activity,OrderAct::class.java)
                startActivity(i)
            }

            , Response.ErrorListener { error ->

                Toast.makeText(activity,error.message, Toast.LENGTH_LONG).show()
            })
            rq.add(sr)
        }
        return v
    }



}
