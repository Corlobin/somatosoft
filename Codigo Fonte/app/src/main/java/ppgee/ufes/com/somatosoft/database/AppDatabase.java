package ppgee.ufes.com.somatosoft.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ppgee.ufes.com.somatosoft.database.dao.UserDao;
import ppgee.ufes.com.somatosoft.database.entity.User;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
