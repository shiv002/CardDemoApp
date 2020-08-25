package com.dance.carddemoapp.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.dance.carddemoapp.R
import com.dance.carddemoapp.databinding.DetailBinding
import com.makeramen.roundedimageview.RoundedImageView

/**
 * @author:Shivani Bajpai
 * @description: This class show user detail
 */
class UserDetailActivity : AppCompatActivity() {
    private var binding: DetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.txt_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val intent: Intent = getIntent()
        setupUI(intent)

    }

    private fun setupUI(intent: Intent) {
        binding?.userdetail = intent.getParcelableExtra("USERDETAIL")
        binding?.executePendingBindings()
        loadimage(binding?.imageView1, binding?.userdetail?.picture)
    }

    @BindingAdapter("app:src")
    fun loadimage(imageView: RoundedImageView?, imageUrl: String?) {
        if (imageView != null) {
            Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView)
        };
    }


}