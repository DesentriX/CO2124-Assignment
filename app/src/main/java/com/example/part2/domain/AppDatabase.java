package com.example.part2.domain;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Course.class, Student.class, CourseStudentCrossRef.class}, version = 4 , exportSchema = false)

public abstract class AppDatabase  extends RoomDatabase {

        public abstract CourseDao courseDao();
        public abstract StudentDao studentDao();
        public abstract StudentCourseDao studentCourseDao();
        public abstract EnrollmentDao enrollmentDao();


        private static volatile AppDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static AppDatabase getDatabase(final Context context) {
                if (INSTANCE == null) {
                        synchronized (AppDatabase.class) {
                                if (INSTANCE == null) {
                                        INSTANCE = Room.databaseBuilder(
                                                context
                                                        .getApplicationContext(),
                                                AppDatabase.class,
                                                "cms_db"
                                        ).fallbackToDestructiveMigration().build();
                                }
                        }
                }
                return INSTANCE;
        }

}
