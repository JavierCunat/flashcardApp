package com.example.flashcardapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashcardapp.Flashcard
import com.example.flashcardapp.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
