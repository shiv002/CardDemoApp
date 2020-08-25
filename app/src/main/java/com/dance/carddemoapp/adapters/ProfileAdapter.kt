package com.dance.carddemoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dance.carddemoapp.R
import com.dance.carddemoapp.databinding.ItemBinding
import com.dance.carddemoapp.interfaces.MenuItemClickListener
import com.dance.carddemoapp.models.ResultModel
import com.makeramen.roundedimageview.RoundedImageView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author:Shivani bajpai
 * @description: This adapter manages cards of cardstackView,changing color of images on click
 * databinding used to set data
 */
class ProfileAdapter(var context: Context, menuItemClickListener: MenuItemClickListener) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var profiles: ArrayList<ResultModel>? = null
    private var menuItemClick: MenuItemClickListener = menuItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.card_item_layout,
            parent,
            false
        )


    )

    override fun getItemCount() = profiles?.size ?: 0


    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        profiles?.let {
            holder.binding.profile = it[position].user
            holder.binding.executePendingBindings()
        }

        holder.binding.userName.setOnClickListener(View.OnClickListener {
            setPrimaryColor(holder.binding.userName)
            setGrayColor(holder.binding.calendar)
            setGrayColor(holder.binding.call)
            setGrayColor(holder.binding.location)
            setGrayColor(holder.binding.password)

            holder.binding.txtAddValue.setText(holder.binding.profile?.username)
            holder.binding.txtAdd.text = context.getString(R.string.txt_userName)
        })

        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
        val datenow = holder.binding.profile?.dob
        val strDate: String = formatter.format(Date(Integer.parseInt(datenow.toString()) * 1000L))

        holder.binding.calendar.setOnClickListener(View.OnClickListener {
            setGrayColor(holder.binding.userName)
            setPrimaryColor(holder.binding.calendar)
            setGrayColor(holder.binding.call)
            setGrayColor(holder.binding.location)
            setGrayColor(holder.binding.password)

            holder.binding.txtAddValue.setText(strDate)
            holder.binding.txtAdd.setText(context.getString(R.string.txt_birthday))
        })

        holder.binding.call.setOnClickListener(View.OnClickListener {
            setGrayColor(holder.binding.userName)
            setGrayColor(holder.binding.calendar)
            setPrimaryColor(holder.binding.call)
            setGrayColor(holder.binding.location)
            setGrayColor(holder.binding.password)

            holder.binding.txtAddValue.setText(holder.binding.profile?.phone)
            holder.binding.txtAdd.setText(context.getString(R.string.txt_phn))
        })

        holder.binding.location.setOnClickListener(View.OnClickListener {
            setGrayColor(holder.binding.userName)
            setGrayColor(holder.binding.calendar)
            setGrayColor(holder.binding.call)
            setPrimaryColor(holder.binding.location)
            setGrayColor(holder.binding.password)

            holder.binding.txtAddValue.setText(holder.binding.profile?.location?.street)
            holder.binding.txtAdd.setText(context.getString(R.string.txt_add))
        })

        holder.binding.password.setOnClickListener(View.OnClickListener {
            setGrayColor(holder.binding.userName)
            setGrayColor(holder.binding.calendar)
            setGrayColor(holder.binding.call)
            setGrayColor(holder.binding.location)
            setPrimaryColor(holder.binding.password)

            holder.binding.txtAddValue.setText(holder.binding.profile?.password)
            holder.binding.txtAdd.setText(context.getString(R.string.txt_password))
        })

        loadimage(holder.binding.imageView1, holder.binding?.profile?.picture)

        holder.binding.imgFav.setOnClickListener(View.OnClickListener {
            menuItemClick.menuItemClick(
                0,
                position
            )
        })
        holder.binding.imgRewind.setOnClickListener(View.OnClickListener {
            menuItemClick.menuItemClick(
                1,
                position
            )
        })
        holder.binding.imgDetail.setOnClickListener(View.OnClickListener {
            menuItemClick.menuItemClick(
                2,
                position
            )
        })


    }

    fun setProfiles(profiles: ArrayList<ResultModel>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    @BindingAdapter("app:src")
    fun loadimage(imageView: RoundedImageView, imageUrl: String?) {
        Glide.with(imageView.getContext())
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_user)
            .into(imageView)
    }


    private fun setPrimaryColor(image: ImageView) {
        image.setColorFilter(
            ContextCompat.getColor(context, R.color.colorPrimary),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun setGrayColor(image: ImageView) {
        image.setColorFilter(
            ContextCompat.getColor(context, R.color.lighter_gray),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }


}


