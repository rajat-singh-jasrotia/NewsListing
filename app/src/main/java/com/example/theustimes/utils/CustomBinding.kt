package com.example.theustimes.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholder", "error"], requireAll = false)
fun ImageView.bindImageUrl(imageUri: String?, placeholder: Drawable, error: Drawable) {
    //if (placeholder == null || error == null) return

    Picasso.get().load(imageUri).fit().centerCrop()
        .placeholder(placeholder)
        .error(error)
        .into(this);
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}