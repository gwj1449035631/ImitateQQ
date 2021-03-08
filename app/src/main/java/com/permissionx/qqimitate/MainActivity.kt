package com.permissionx.qqimitate

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.qq_login.*

class MainActivity : AppCompatActivity() {
    private lateinit var sweetAlertDialog: SweetAlertDialog
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
            if (editable.toString().isNotEmpty()){
                qqLogin.isEnabled=true
            }
        }
        inputPassword.addTextChangedListener{editable->
            if (editable.toString().isNotEmpty()){
                qqLogin.background=getDrawable(R.color.colorBlue)
            }else{
                qqLogin.background=getDrawable(R.color.login_color)
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
                    .show();
            }
        }
        findPassword.setOnClickListener {

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
}
