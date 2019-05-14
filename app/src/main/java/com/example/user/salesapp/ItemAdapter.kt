package com.example.user.salesapp

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

@Suppress("DEPRECATION")
class ItemAdapter(var context:Context, var list: ArrayList<Item>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var v:View=LayoutInflater.from(context).inflate(R.layout.item_row,p0,false)
        return ItemHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(list[position].name,list[position].price,list[position].photo,list[position].id)
        }

    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(n:String,p:Double,u:String,item_id:Int){
            itemView.item_name.text=n
            itemView.item_price.text=p.toString()
            var web:String="http://${UserInfo.ip}/Sales/pics/$u"
            web=web.replace(" ","%20")
            Picasso.get().load(web).into(itemView.item_photo)

            itemView.item_add_photo.setOnClickListener {
                UserInfo.itemId=item_id
                var obj=QtyFragment()
                var manager=(itemView.context as Activity).fragmentManager
                obj.show(manager,"Qty")
            }


        }

    }
}