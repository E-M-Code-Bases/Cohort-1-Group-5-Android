package com.dominic.movieswatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.database.MovieDao
import com.dominic.movieswatch.model.Converters

@Database(entities = [Movie::class], version = 2) // Increment version number
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies_database"
                )
                    .addMigrations(MIGRATION_1_2) // Add migration strategy
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Define the migration strategy
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add the new timestamp column
                database.execSQL("ALTER TABLE movies ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}
