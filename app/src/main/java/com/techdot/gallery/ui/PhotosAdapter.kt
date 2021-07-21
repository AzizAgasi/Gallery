package com.techdot.gallery.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.techdot.gallery.databinding.PhotoLayoutBinding
import com.techdot.gallery.models.Photo

class PhotosAdapter(photoList: List<Photo>, mainContext: Context): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private val photos = photoList
    private val context = mainContext

    class ViewHolder(private val binding: PhotoLayoutBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            Glide.with(context)
                .load(Uri.parse(photo.url_s))
                .addListener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e("IMAGE_FAILED", e?.message.toString())
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loadingBar.visibility = View.INVISIBLE
                        binding.photo.setImageDrawable(resource)
                        return true
                    }

                })
                .into(binding.photo)
            binding.photo.contentDescription = photo.title

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.photo.tooltipText = "Title: ${photo.title} \n Owner: ${photo.owner}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}