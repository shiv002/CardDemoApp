package com.dance.carddemoapp.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.dance.carddemoapp.R
import com.dance.carddemoapp.adapters.ProfileAdapter
import com.dance.carddemoapp.interfaces.MenuItemClickListener
import com.dance.carddemoapp.models.ResultModel
import com.dance.carddemoapp.models.UserModel
import com.dance.carddemoapp.utils.MyDataBase
import com.dance.carddemoapp.utils.Retrofitbuilder
import com.dance.carddemoapp.utils.Status
import com.dance.carddemoapp.utils.ViewModelFactory
import com.dance.carddemoapp.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author:Shivani Bajpai
 * @Description: This is main class on screen for showing cards using cardstackview or favorite profile or see details of person
 */

class CardActivity : AppCompatActivity(), CardStackListener, MenuItemClickListener {

    private val adapter = ProfileAdapter(this, this)
    private lateinit var layoutManager: CardStackLayoutManager
    private lateinit var viewModel: MainViewModel
    private var mProfileList: MutableList<ResultModel> = mutableListOf()
    private var db: MyDataBase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = MyDataBase.getMyDataBase(context = this)
        supportActionBar?.title = getString(R.string.app_name)

        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(AccelerateDecelerateInterpolator())
            setStackFrom(StackFrom.Bottom)
            setScaleInterval(0.95f)
            setTranslationInterval(12.0f)
            setMaxDegree(20.0f)
            setSwipeThreshold(0.3f)
            setDirections(Direction.HORIZONTAL)
        }

        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }


        layoutManager.setCanScrollVertical(false)

        setupViewModel()
        setupObserver()
    }


    private fun setupObserver() {
        viewModel.getProfileList().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        mProfileList.clear()
                        resource.data?.let { mainCardModel ->
                            stack_view.visibility = VISIBLE
                            progressBar.visibility = GONE
                            mProfileList = mainCardModel.result
                            adapter.setProfiles(mProfileList as ArrayList<ResultModel>)
                        }
                    }
                    Status.ERROR -> {
                        stack_view.visibility = GONE
                        progressBar.visibility = GONE
                        Toast.makeText(
                            this,
                            "Something went wrong:" + it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = VISIBLE
                        stack_view.visibility = VISIBLE

                    }
                }
            }
        })
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(Retrofitbuilder.apiService))
            .get(MainViewModel::class.java)
    }


    override fun onCardDisappeared(view: View?, position: Int) {
        if(position==mProfileList.size-1)
        {
            Toast.makeText(this,"Profiles End",Toast.LENGTH_SHORT).show()
            finishAffinity()
        }

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {


    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_fav -> {
                GlobalScope.launch {
                    val intent = Intent(this@CardActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }


    override fun menuItemClick(menuposition: Int, itemPosition: Int) {
        if (menuposition == 0) {
            if (mProfileList.size > 0) {
                val mUserDetail: UserModel = mProfileList.get(itemPosition).user
                GlobalScope.launch {
                    db?.myDao()?.addData(mUserDetail)
                    stack_view.swipe()
                }

            } else {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        } else if (menuposition == 1) {
            val setting: RewindAnimationSetting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(100)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .build()
            layoutManager.setRewindAnimationSetting(setting)
            stack_view.rewind()
        } else if (menuposition == 2) {
            if (mProfileList.size > 0) {
                val mUserDetail: UserModel = mProfileList.get(itemPosition).user
                val intent = Intent(this@CardActivity, UserDetailActivity::class.java)
                intent.putExtra("USERDETAIL", mUserDetail)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


}