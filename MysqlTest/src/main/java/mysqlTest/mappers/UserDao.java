package mysqlTest.mappers;

import mysqlTest.entity.User;

public interface UserDao {
    public User getUserById(String id);
}
