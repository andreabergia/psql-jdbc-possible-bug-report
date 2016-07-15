import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestReturningKey {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/sampledb", "sampledb", "")) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Documents (name) VALUES (?)", new String[]{"ID"})) {
                ps.setString(1, "DocName");
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        throw new RuntimeException("Should have been able to retrieve the generated keys");
                    }
                    int generatedKey = generatedKeys.getInt(1);
                    System.out.println("The generated key is " + generatedKey);
                }
            }
        }
    }
}
