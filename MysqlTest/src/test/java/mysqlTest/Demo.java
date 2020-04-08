package mysqlTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Demo {

    public static void main(String[] args) {
        InputStream in = ClassLoader.getSystemResourceAsStream("");
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession(true);
        session.selectOne("","");

    }






}
