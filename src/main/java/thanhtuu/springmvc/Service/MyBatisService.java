package thanhtuu.springmvc.Service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisService {
	   private static String resource = "xml/mybatisConfig.xml";
	    private static Reader reader = null;
	    private static SqlSessionFactory sqlMapper = null;
	 
	    public static synchronized SqlSessionFactory getSessionFactory() {
	        if (sqlMapper == null) {
	            try {
	                reader = Resources.getResourceAsReader(resource);
	                sqlMapper = new SqlSessionFactoryBuilder().build(reader);
	            } catch (IOException e) {
	                System.err.println("Error SQL Session: " + e.toString());
	            }
	        }
	 
	        return sqlMapper;
	    }
}
