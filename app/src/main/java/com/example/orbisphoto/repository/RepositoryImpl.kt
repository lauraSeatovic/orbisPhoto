package com.example.orbisphoto.repository

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.graphics.vector.Group
import com.example.orbisphoto.data.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinApplication.Companion.init
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RepositoryImpl() : Repository {
    private val ref =
        FirebaseDatabase.getInstance("https://orbisphoto-db5d6-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference()
    private val refStorage = FirebaseStorage.getInstance().reference
    private val auth = Firebase.auth
    private var groups = mutableListOf<String>()

    init {
        this.myGroups()
    }


    @ExperimentalCoroutinesApi
    fun getGroups() = callbackFlow<List<GroupCard>> {
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("groups", groups.toString())
                val items = dataSnapshot.children.filter { groups.contains(it.child("groupId").value.toString()) }.map {
                    Log.i("cont", it.child("groupId").value.toString())
                    GroupCard(
                        it.child("groupId").value.toString(),
                        it.child("name").value.toString(),
                        it.child("image").value.toString(),
                        it.child("color").value.toString()
                    )
                }
                this@callbackFlow.trySendBlocking(items)
            }
        }

        ref.child("groups").addValueEventListener(postListener)

        awaitClose {
            ref.child("groups").removeEventListener(postListener)
        }
    }

    @ExperimentalCoroutinesApi
    fun showGroup(groupId: String) = callbackFlow<Group> {
        val groupListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = Group(
                    dataSnapshot.child("groupId").value.toString(),
                    dataSnapshot.child("name").value.toString(),
                    dataSnapshot.child("password").value.toString(),
                    dataSnapshot.child("image").value.toString(),
                    dataSnapshot.child("color").value.toString(),
                    dataSnapshot.child("posts").children.map { post ->
                        Post(
                            post.child("postId").value.toString(),
                            post.child("groupId").value.toString(),
                            post.child("description").value.toString(),
                            post.child("image").value.toString(),
                            post.child("date").value.toString()
                        )
                    }

                )
                this@callbackFlow.trySendBlocking(items)
            }
        }


        ref.child("groups").child(groupId).addValueEventListener(groupListener)

        awaitClose {
            ref.child("groups").removeEventListener(groupListener)
        }
    }


    fun uploadImageToFirebase(fileUri: Uri): String {
        var fileName = ""
        if (fileUri != null) {
            fileName = UUID.randomUUID().toString() + ".jpg"
            refStorage.child("images/$fileName").putFile(fileUri)
        }
        return fileName
    }

    fun saveGroup(
        name: String,
        password: String,
        image: String,
        color: Int
    ): String {
        val groupId = ref.child("groups").push().key
        refStorage.child("images/${image}").downloadUrl.addOnSuccessListener {
            val group = Group(groupId, name, password, it.toString(), color.toString(), null)
            ref.child("groups").child(groupId!!).setValue(group)
        }.addOnCompleteListener{
            this.joinGroup(groupId!!, password)
        }
        return groupId!!
    }

    fun savePost(
        groupId: String,
        photo: String,
        description: String
    ): String {
        val postId = ref.child("groups").child(groupId).child("posts").push().key

        refStorage.child("images/${photo}").downloadUrl.addOnSuccessListener {
            val post = Post(
                postId,
                groupId,
                description,
                it.toString(),
                SimpleDateFormat("dd/M/yyyy").format(Date())
            )
            ref.child("groups").child(groupId).child("posts").child(postId!!).setValue(post)
        }
        return postId!!
    }

    fun getRecentPosts() = callbackFlow<List<Post>> {

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = mutableListOf<Post>()
                dataSnapshot.children.filter { groups.contains(it.child("groupId").value.toString()) }.forEach {
                    items.addAll(it.child("posts").children.filter { post ->
                        post.child("date").value.toString() == SimpleDateFormat(
                            "dd/M/yyyy"
                        ).format(Date())
                    }.map { post ->
                        Post(
                            post.child("postId").value.toString(),
                            post.child("groupId").value.toString(),
                            post.child("description").value.toString(),
                            post.child("image").value.toString(),
                            post.child("date").value.toString()
                        )
                    })
                }
                Log.i("items", items.toString())
                Log.i("date", Date().toString())
                this@callbackFlow.trySendBlocking(items)
            }
        }

        ref.child("groups").addValueEventListener(postListener)

        awaitClose {
            ref.child("groups").removeEventListener(postListener)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun signUp(email: String, password: String, username: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signup", "createUserWithEmail:success")
                    val newUser = User(auth.currentUser!!.uid, username, password)
                    ref.child("users").child(auth.currentUser!!.uid).setValue(newUser)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("signup", "createUserWithEmail:failure", task.exception)
                }
            }

    }

    fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("login", "logged in.")
                this.myGroups()
            } else {
                Log.d("fail", "failed to log in")
            }
        }

    }

    fun joinGroup(key: String, password: String){
        ref.child("groups").child(key).get().addOnSuccessListener {
            if((it.child("groupId").value == key) and (it.child("password").value == password)){
                Log.i("firebase", "Match")
                ref.child("users").child(auth.currentUser!!.uid).child("groups").get().addOnCompleteListener(){ task ->
                    if (task.isSuccessful) {
                        ref.child("users").child(auth.currentUser!!.uid).child("groups").child(task.result.childrenCount.toString()).setValue(key)
                        this.myGroups()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("signup", "createUserWithEmail:failure", task.exception)
                    }
                }
            }else{
                Log.i("firebase", "Group does not exist")
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun myGroups(){
        groups = mutableListOf()
        if(auth.currentUser != null) {
            ref.child("users").child(auth.currentUser!!.uid).child("groups").get()
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        groups.addAll(if(task.result.value == null) emptyList() else  task.result.value as List<String>)
                        Log.i("mygroups", groups.toString())

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("signup", "createUserWithEmail:failure", task.exception)
                    }
                }
        }

    }


}

