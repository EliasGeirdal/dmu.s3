import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferApp {
	public static void main(String[] args) {
		try {
			Connection minConnection;
			minConnection = DriverManager.getConnection(
					"jdbc:sqlserver://DESKTOP-A9FL7AN\\SQLEXPRESS;databaseName=bank;user=sa;password=cookie;");
			
			System.out.println("Opret overførsel ");
//			BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Indtast frakonto regNr.: ");
			String fraRegNr = "1234"; // inLine.readLine();
			System.out.println("Indtast frakonto kontoNr.: ");
			String fraKontoNr = "1234567"; // inLine.readLine();

			System.out.print("Indtast tilkonto regNr.: ");
			String tilRegNr = "1216";// inLine.readLine();
			System.out.println("Indtast tilkonto kontoNr.: ");
			String tilKontoNr = "2234567";// inLine.readLine();

			System.out.println("Indtast beløb: ");
			double beløb = 500.00;// Double.parseDouble(inLine.readLine());

			String chkFraKonto = "select saldo from Konto where Konto.ktoNr = "
					+ fraKontoNr
					+ "and Konto.regNr = " + fraRegNr;

			String chkTilKonto = "select saldo from Konto where Konto.ktoNr = " + tilKontoNr + "and Konto.regNr = "
					+ tilRegNr;

			PreparedStatement prestmt = minConnection.prepareStatement(chkFraKonto);

			ResultSet res = prestmt.executeQuery();
			res.next();
			System.out.println(res.getString(1));
			double fraSaldo = Double.parseDouble(res.getString(1));
			System.out.println(fraSaldo);
			if (fraSaldo > beløb) {
				System.out.println("Saldo er større end beløb");
				prestmt = minConnection.prepareStatement(chkTilKonto);

				res = prestmt.executeQuery();
				res.next();
				double tilSaldo = Double.parseDouble(res.getString(1));
				System.out.println(tilSaldo);

				fraSaldo -= beløb;
				tilSaldo += beløb;

				String updateFraKonto = "update Konto set saldo = " + fraSaldo
						+ "where Konto.ktoNr = "
								+ fraKontoNr
								+ "and Konto.regNr = " + fraRegNr;
				prestmt = minConnection.prepareStatement(updateFraKonto);

				prestmt.executeUpdate();

				String updateTilKonto = "update Konto set saldo = " + tilSaldo
						+ "where Konto.ktoNr = " + tilKontoNr + "and Konto.regNr = "
								+ tilRegNr;
				prestmt = minConnection.prepareStatement(updateTilKonto);

				prestmt.executeUpdate();
			}

			prestmt = minConnection.prepareStatement(chkFraKonto);

			res = prestmt.executeQuery();
			res.next();
			System.out.println(res.getString(1));

			prestmt = minConnection.prepareStatement(chkTilKonto);

			res = prestmt.executeQuery();
			res.next();
			System.out.println(res.getString(1));

			if (prestmt != null)
				prestmt.close();
			if (minConnection != null)
				minConnection.close();
		} catch (SQLException e) {
			System.out.println("fejl:  " + e.getErrorCode());
			System.out.println("fejl:  " + e.getMessage());
		} catch (Exception e) {
			System.out.println("fejl: " + e.getMessage());
		}
	}
}
