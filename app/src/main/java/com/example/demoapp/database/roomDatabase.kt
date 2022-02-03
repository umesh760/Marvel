package com.example.demoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demoapp.model.ResultsItem
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

import androidx.room.migration.Migration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [ResultsItem::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class roomDatabase : RoomDatabase() {
    abstract fun ResultsItemDao(): DataItemDao

    private val NUMBER_OF_THREADS = 1
    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    }

    companion object {
        var INSTANCE: roomDatabase? = null

        fun getDatabase(context: Context): roomDatabase {
            if (INSTANCE == null) {
                synchronized(roomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            roomDatabase::class.java, "demoDatabase2022"
                        ).allowMainThreadQueries().fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}