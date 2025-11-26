import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class SaveNumbersInDB {
    private static final String INSERT_SQL =
            "INSERT INTO results (n1, n2, n3, n4, n5, n6) VALUES (?, ?, ?, ?, ?, ?)";

    public static void saveWinningNumbers (Set<Integer> winning) {
        try (Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){

            Iterator<Integer> iterator = winning.iterator();
            for (int i = 1; i <= 6; i++){
                preparedStatement.setInt(i, iterator.next());
            }
            preparedStatement.executeUpdate();
            System.out.println("Vindertal er gemt i database");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
