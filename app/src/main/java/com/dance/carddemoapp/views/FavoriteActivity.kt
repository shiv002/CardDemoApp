package com.dance.carddemoapp.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dance.carddemoapp.R
import com.dance.carddemoapp.adapters.UserFavAdapter
import com.dance.carddemoapp.models.UserModel
import com.dance.carddemoapp.utils.MyDataBase
import com.dance.carddemoapp.utils.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author:Shivani Bajpai
 * @description: favorite user class
 */

class FavoriteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mUserList: MutableList<UserModel> = mutableListOf()
    private lateinit var adapter: UserFavAdapter
    private var db: MyDataBase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        supportActionBar?.title = getString(R.string.txt_fav)

        db = MyDataBase.getMyDataBase(context = this)
        recyclerView = findViewById(R.id.recList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupUI()
    }


    private fun setupUI() {
        GlobalScope.launch(Dispatchers.IO) {
            mUserList = db?.myDao()?.getMyData() as MutableList<UserModel>
            withContext(Dispatchers.Main)
            {
                adapter = UserFavAdapter(mUserList as ArrayList<UserModel>, this,db)
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = adapter
            }
        }

        enableSwipeToDeleteUndo()
    }

    private fun enableSwipeToDeleteUndo() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(this,R.drawable.ic_dump) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                val item: UserModel = adapter.getData()!!.get(position)
                adapter.removeItem(position)

                val snackbar = Snackbar
                    .make(
                        coordinatorLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO", View.OnClickListener() {
                    adapter.restoreItem(item, position)
                    recyclerView.scrollToPosition(position)
                })

                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()

            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerView)
    }
}




