import com.mysql.jdbc.Statement;
import java.sql.SQLException;

public class UsersInfo extends Database{
    private Integer setAmount;
    private String symbol;

    public UsersInfo(Integer setAmount, String symbol) {
        this.setAmount = setAmount;
        this.symbol = symbol;
        this.insertData(setAmount, symbol); // iskvieciamas funkcija
    }

      private void insertData(Integer setAmount, String symbol) {
        try {
            String insertQueryStatement = "INSERT INTO `usersinfo` (`set_amount`, `symbol`) VALUES (?, ?);";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement,  Statement.RETURN_GENERATED_KEYS);
            dbPrepareStatement.setInt(1, setAmount);
            dbPrepareStatement.setString(2, symbol);
            dbPrepareStatement.execute(); // execute insert SQL statement
        } catch (SQLException e) {
            System.out.print("Kazkas blogai");
            e.printStackTrace();
        }

    }

}
