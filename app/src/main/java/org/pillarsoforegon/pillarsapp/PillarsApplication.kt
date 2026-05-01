package org.pillarsoforegon.pillarsapp

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class PillarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        val options = FirebaseOptions.Builder()
            .setApiKey("AIzaSyAOotggMrhBYv9l8lyP0IRhjTbsz-oNpf0")
            .setApplicationId("1:231241868866:web:3be2ccd0f4c6ac16faafdb")
            .setDatabaseUrl("https://pillars-a3fff-default-rtdb.firebaseio.com")
            .setProjectId("pillars-a3fff")
            .setStorageBucket("pillars-a3fff.firebasestorage.app")
            .build()

        FirebaseApp.initializeApp(this, options)
    }
}
