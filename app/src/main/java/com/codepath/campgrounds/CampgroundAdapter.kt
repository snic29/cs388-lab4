package com.codepath.campgrounds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Intent

private const val TAG = "CampgroundAdapter"

class CampgroundAdapter(private val context: Context, private val campgrounds: List<Campground>) :
    RecyclerView.Adapter<CampgroundAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_campground, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the individual campground and bind to holder
        val campground = campgrounds[position]
        holder.bind(campground)
    }

    override fun getItemCount() = campgrounds.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // Create member variables for any view that will be set
        private val nameTextView = itemView.findViewById<TextView>(R.id.campgroundName)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.campgroundDescription)
        private val locationTextView = itemView.findViewById<TextView>(R.id.campgroundLocation)
        private val imageView = itemView.findViewById<ImageView>(R.id.campgroundImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(campground: Campground) {
            // Set item views based on views and data model
            nameTextView.text = campground.name
            descriptionTextView.text = campground.description
            locationTextView.text = campground.latLong

            Glide.with(context)
                .load(campground.imageUrl)
                .into(imageView)
        }

        override fun onClick(v: View?) {
            // Get selected campground
            val campground = campgrounds[absoluteAdapterPosition]

            // Navigate to Details screen and pass selected campground
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CAMPGROUND_EXTRA, campground)
            context.startActivity(intent)

        }
    }
}
