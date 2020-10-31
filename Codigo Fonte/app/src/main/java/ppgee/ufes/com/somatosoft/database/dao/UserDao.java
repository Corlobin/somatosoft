package ppgee.ufes.com.somatosoft.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import ppgee.ufes.com.somatosoft.database.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE login LIKE :login LIMIT 1")
    User findByLogin(String login);

    @Query("SELECT * FROM user WHERE login = :login AND " +
            "password = :password LIMIT 1")
    User findByLoginAndPassword(String login, String password);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}