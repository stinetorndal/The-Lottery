import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoStats {

    //------Returnerer hyppigste tal------
    public static Map<Integer, Integer> getMostFrequent(String sql) {
        Map<Integer, Integer> frequent = new LinkedHashMap<>();
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                frequent.put(resultSet.getInt("number"), resultSet.getInt("frequency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return frequent;
    }

    //------Returnerer antal trækninger / en enkelt talværdi------
    public static int getSingleValue(String sql, String columnLabel) {

        //------Bemærk Conncection, Statement og Resultset------
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt(columnLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
