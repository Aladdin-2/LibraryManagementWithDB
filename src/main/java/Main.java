import DBConnection.DBConnection;
import DBProcess.DBProcess;

public class Main {
    public static void main(String[] args) {
        DBConnection.getConnection();
        DBProcess.shoBokMenu();
        DBConnection.closeConnection();
    }
}
