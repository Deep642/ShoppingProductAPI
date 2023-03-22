package com.example.api

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity,val productArrayList: List<Product>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView=LayoutInflater.from(context).inflate(R.layout.eachrowitem,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=productArrayList[position]
        holder.title.text=currentItem.title
        Picasso.get().load(currentItem.thumbnail).into(holder.image)

        if(currentItem.rating>=4.0&&currentItem.rating<5){
            holder.img5.alpha= (currentItem.rating-4).toFloat()
        }
        if(currentItem.rating>=3.0&&currentItem.rating<4){
            holder.img4.alpha= (currentItem.rating-3).toFloat()
        }
        if(currentItem.rating>=2.0&&currentItem.rating<3){
            holder.img3.alpha= (currentItem.rating-2).toFloat()
        }
        if(currentItem.rating>=1.0&&currentItem.rating<2){
            holder.img2.alpha= (currentItem.rating-1).toFloat()
        }
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
            var title=itemView.findViewById<TextView>(R.id.productTitle)
            var image=itemView.findViewById<ShapeableImageView>(R.id.productImage)
            var img1=itemView.findViewById<ImageView>(R.id.imageView)
            var img2=itemView.findViewById<ImageView>(R.id.imageView2)
            var img3=itemView.findViewById<ImageView>(R.id.imageView3)
            var img4=itemView.findViewById<ImageView>(R.id.imageView4)
            var img5=itemView.findViewById<ImageView>(R.id.imageView5)
    }
}