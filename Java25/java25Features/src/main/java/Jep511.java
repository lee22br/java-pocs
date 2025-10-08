
import module java.sql;


public class Jep511 {

    public static void main(String[] args) throws SQLException {
        System.out.println("Test Module Import Declaration");

        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        System.out.println("Connection created: " + (conn != null));

        ConnectionPoolDataSource ds = new org.h2.jdbcx.JdbcDataSource();
        DataSource dataSource = (DataSource) ds;
        System.out.println("Datasource created: " + dataSource);
    }

}


//    public static Map<Character, List<String>> groupByFirstLetter(String... values) {
//        return Stream.of(values).collect(
//                Collectors.groupingBy(s -> Character.toUpperCase(s.charAt(0))));
//    }

