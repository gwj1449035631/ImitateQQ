package com.permissionx.qqimitate

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.permissionx.qqimitate.model.QQ
import com.permissionx.qqimitate.ui.login.LoginAdapter
import kotlinx.android.synthetic.main.qq_login.*

class MainActivity : AppCompatActivity() {
    private var flagBtn=true
    private var flagEye=true
    private var qqs=ArrayList<QQ>()
    lateinit var loginAdapter: LoginAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT>=21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.qq_login)
        inputAccount.addTextChangedListener{editable->
            qqLogin.isEnabled = editable.toString().isNotEmpty()
        }
        inputPassword.addTextChangedListener{editable->
            if (editable.toString().isNotEmpty()){
                qqLogin.background=getDrawable(R.color.colorBlue)
            }else{
                qqLogin.background=getDrawable(R.color.login_color)
            }
        }
        inputAccount.setOnFocusChangeListener { v, hasFocus ->
            val et= v as EditText
            if (!hasFocus){
                et.hint=et.tag.toString()
            }else{
                val hint=et.hint.toString()
                et.hint=null
                et.tag=hint
            }
        }
        inputPassword.setOnFocusChangeListener { v, hasFocus ->
            val et= v as EditText
            if (!hasFocus){
                et.hint=et.tag.toString()
            }else{
                val hint=et.hint.toString()
                et.hint=null
                et.tag=hint
            }
        }
        qqLogin.setOnClickListener {
            if(inputAccount.text.toString() == "123456" && inputPassword.text.toString() == "123456") {
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("You clicked the button!")
                    .show()
            }else{
                Toast.makeText(this,"$inputPassword    $inputAccount",Toast.LENGTH_SHORT).show()
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .show()
            }
        }
        findPassword.setOnClickListener {

        }
        qQs()
        loginAdapter=LoginAdapter(qqs)
        val linearLayoutManager=LinearLayoutManager(this)
        recyclerView.apply {
            adapter=loginAdapter
            layoutManager=linearLayoutManager
        }
        recyclerViewBtn.setOnClickListener {
            if (flagBtn){
                recyclerViewBtn.setImageResource(R.mipmap.triangle_up)
                listCard.visibility=View.VISIBLE
                flagBtn=false
            }else{
                recyclerViewBtn.setImageResource(R.mipmap.triangle_down)
                listCard.visibility=View.INVISIBLE
                flagBtn=true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                Toast.makeText(this,"You clicked home.",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun qQs(){
        for (i in 0..10){
            qqs.add(QQ(123456, 123456.toString()))
        }
    }
}
