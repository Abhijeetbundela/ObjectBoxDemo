package com.oss.demo.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.demo.adapter.UserAdapter
import com.oss.demo.databinding.ActivityHomeBinding
import com.oss.demo.model.User
import com.oss.demo.viewModel.UserViewModel

class HomeActivity : AppCompatActivity(), UserAdapter.UserAdapterInterface {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userAdapter: UserAdapter
    private var userList = ArrayList<User>()

    private val userViewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpUI()

        binding.createBtn.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }

        userViewModel.getAllUser().observe(this, {
            userAdapter.addUserData(it as ArrayList<User>)
        })

    }

    private fun setUpUI() {
        userAdapter = UserAdapter(this, userList, this)

        binding.dataRecyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            recycledViewPool.setMaxRecycledViews(1, 0)
        }
    }

    override fun delete(item: User) {
        userViewModel.delete(item)
    }

    override fun update(item: User) {
        val intent = Intent(this, AddUserActivity::class.java).apply {
            putExtra("id", item.id.toString())
            putExtra("userName", item.userName)
            putExtra("email", item.userEmail)
        }
        startActivity(intent)

    }
}