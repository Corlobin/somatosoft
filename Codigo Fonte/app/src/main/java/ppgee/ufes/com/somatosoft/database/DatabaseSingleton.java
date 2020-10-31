package ppgee.ufes.com.somatosoft.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (DatabaseSingleton.appDatabase == null) {
            DatabaseSingleton.appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name")
                    //.allowMainThreadQueries()
                    .build();
        }
        return DatabaseSingleton.appDatabase;
    }
}