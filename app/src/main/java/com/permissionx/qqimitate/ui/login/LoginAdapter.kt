package com.permissionx.qqimitate.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.permissionx.qqimitate.R
import com.permissionx.qqimitate.model.QQ
import kotlinx.android.synthetic.main.qq_item.view.*

class LoginAdapter(private val qqList: List<QQ>):RecyclerView.Adapter<LoginAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val qqAccount:TextView=view.qqAccount
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.qq_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=qqList.size

    override fun onBindViewHolder(holder: LoginAdapter.ViewHolder, position: Int) {
        val qq=qqList[position]
        holder.qqAccount.text= qq.account.toString()
    }
}