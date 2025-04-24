package com.ucb.framework.dollar

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ucb.data.dollar.IRealDatabaseDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseRealDatabase: IRealDatabaseDataSource {

    override fun getDollarUpdates(): Flow<String> = callbackFlow {

        val callback = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                close(p0.toException())
            }
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                if (value != null) {
                    trySend(value)
                }
            }
        }

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("app_dollar")
        myRef.addValueEventListener(callback)

        awaitClose {
            myRef.removeEventListener(callback)
        }
    }
}