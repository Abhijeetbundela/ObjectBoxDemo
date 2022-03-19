package com.oss.demo.viewModel

import androidx.lifecycle.ViewModel
import com.oss.demo.database.ObjectBox
import com.oss.demo.model.User
import com.oss.demo.model.User_
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel : ViewModel() {

    private val coroutineContext: CoroutineContext get() = Dispatchers.Main

    fun insert(data: User) = CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
        ObjectBox.boxStore.boxFor(User::class.java).put(data)
    }

    fun delete(data: User) = CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
        val userBox = ObjectBox.boxStore.boxFor(User::class.java)
        userBox.remove(data)
    }

    fun update(data: User) = CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
        ObjectBox.boxStore.boxFor(User::class.java).put(data)
    }

    fun getAllUser(): ObjectBoxLiveData<User> {
        val usersQuery = ObjectBox.boxStore.boxFor(User::class.java).query()
            .orderDesc(User_.id)
            .build()
        return ObjectBoxLiveData(usersQuery)
    }

    fun deleteAll() {
        ObjectBox.boxStore.boxFor(User::class.java).removeAll()
    }
}