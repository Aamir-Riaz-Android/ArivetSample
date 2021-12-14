package com.example.arivetsamplechallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.data.local.dao.UserDao
import com.example.arivetsamplechallenge.utils.Constansts.Companion.APP_DATABASE
import com.example.arivetsamplechallenge.data.local.data_converters.DataConverter


@Database(entities = [UsersInfo::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDetailsDao(): UserDao

    companion object {
        @Volatile //tells thread that this instance value is changed
        var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java,
                        APP_DATABASE
                    )
                        .fallbackToDestructiveMigration() // this is only for testing,
                        .allowMainThreadQueries()         //migration will be added for real cases
                        .build()
                    INSTANCE = instance

                    instance
                }
            }


        }
    }
}
/*private var instance: AppDatabase?=null
fun getDatabase(context:Context): AppDatabase {
    if(instance ==null){
        synchronized(this) {
            instance = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java,
                APP_DATABASE
            )
                .fallbackToDestructiveMigration() // this is only for testing,
                //migration will be added for release
                .allowMainThreadQueries()
                .build()
        }
    }
    return instance!!
}
}
}*/