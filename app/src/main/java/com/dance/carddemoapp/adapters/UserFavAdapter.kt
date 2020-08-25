package com.dance.carddemoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dance.carddemoapp.databinding.UserBinding
import com.dance.carddemoapp.models.UserModel
import com.dance.carddemoapp.utils.MyDataBase
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.*

/**
 * @author:Shivani bajpai
 * @description: This class manages favorite users . Removing user ,undo user on click ,data is set by data binding
 */
class UserFavAdapter(
    private val userList: ArrayList<UserModel>,
    private var context: CoroutineScope,
    private var db: MyDataBase?
) :
    RecyclerView.Adapter<UserFavAdapter.DataViewHolder>() {

    inner class DataViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.userDetail = userList.get(adapterPosition)
            binding.executePendingBindings()
            loadimage(binding.imageView1, binding.userDetail?.picture)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserBinding.inflate(inflater, null, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()

    }

    fun removeItem(position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            db!!.myDao().delete(userList.get(position))
            withContext(Dispatchers.Main)
            {
                userList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    fun restoreItem(item: UserModel, position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main)
            {
                userList.add(position, item)
                notifyItemInserted(position)
            }
            db!!.myDao().addData(userList.get(position))
        }

    }

    @BindingAdapter("app:src")
    fun loadimage(imageView: RoundedImageView, imageUrl: String?) {
        Glide.with(imageView.getContext())
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun getData(): ArrayList<UserModel>? {
        return userList
    }

}