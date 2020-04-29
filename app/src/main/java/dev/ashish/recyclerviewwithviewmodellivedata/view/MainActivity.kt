package dev.ashish.recyclerviewwithviewmodellivedata.view

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ashish.recyclerviewwithviewmodellivedata.R
import dev.ashish.recyclerviewwithviewmodellivedata.adapter.UserDetailsAdapter
import dev.ashish.recyclerviewwithviewmodellivedata.databinding.ActivityMainBinding
import dev.ashish.recyclerviewwithviewmodellivedata.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Obtain ViewModel from ViewModelProviders
    private val userViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    private lateinit var userDetailsAdapter: UserDetailsAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        userViewModel.getUserListLiveData().observe(this, Observer {

            Log.d("MainActivity","observe")

            userDetailsAdapter = UserDetailsAdapter(this@MainActivity, it)
            recyclerView.adapter = userDetailsAdapter

        })


    }

}
