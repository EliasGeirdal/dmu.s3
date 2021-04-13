package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class opgave03 {
	public static void main(String[] args) {
		try {
			Connection minConnection;
			minConnection = DriverManager.getConnection(
					"jdbc:sqlserver://DESKTOP-A9FL7AN\\SQLEXPRESS;databaseName=bank;user=sa;password=cookie;");
			
			System.out.println("Opret overførsel ");
			BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Indtast frakonto regNr.: ");
			String fraRegNr = "1234"; // inLine.readLine();
			System.out.println("Indtast frakonto kontoNr.: ");
			String fraKontoNr = "1234567"; // inLine.readLine();

			System.out.println("Indtast tilkonto regNr.: ");
			String tilRegNr = "1216";// inLine.readLine();
			System.out.println("Indtast tilkonto kontoNr.: ");
			String tilKontoNr = "2234567";// inLine.readLine();

			System.out.println("Indtast beløb: ");
			double beløb = 200.00;// Double.parseDouble(inLine.readLine());
			System.out.println("Beløb indtastet: " + beløb);

			String chkFraKonto = "select saldo from Konto where Konto.ktoNr = "
					+ fraKontoNr
					+ "and Konto.regNr = " + fraRegNr;

			String chkTilKonto = "select saldo from Konto where Konto.ktoNr = " + tilKontoNr + "and Konto.regNr = "
					+ tilRegNr;

			Statement stmt = minConnection.createStatement();
			stmt.execute("set transaction isolation level repeatable read");
			stmt.execute("begin tran");

			ResultSet res = stmt.executeQuery(chkFraKonto);
			res.next();
			double fraSaldo = Double.parseDouble(res.getString(1));
			System.out.println("Frasaldo: " + fraSaldo);
			if (fraSaldo > beløb) {
				System.out.println("Saldo er større end beløb");

				res = stmt.executeQuery(chkTilKonto);
				res.next();
				double tilSaldo = Double.parseDouble(res.getString(1));
				System.out.println("Tilsaldo: " + tilSaldo);

				fraSaldo -= beløb;
				tilSaldo += beløb;

				System.out.println("Fremprovokere en fejl: ");
				inLine.readLine();
				
				String updateFraKonto = "update Konto set saldo = " + fraSaldo
						+ "where Konto.ktoNr = "
								+ fraKontoNr
								+ "and Konto.regNr = " + fraRegNr;

				stmt.executeUpdate(updateFraKonto);

				String updateTilKonto = "update Konto set saldo = " + tilSaldo
						+ "where Konto.ktoNr = " + tilKontoNr + "and Konto.regNr = "
								+ tilRegNr;

				stmt.executeUpdate(updateTilKonto);
			}

			// check to see if new values are correctly updated.
			res = stmt.executeQuery(chkFraKonto);
			res.next();
			System.out.println("Ny fraSaldo: " + res.getString(1));

			res = stmt.executeQuery(chkTilKonto);
			res.next();
			System.out.println("Ny tilSaldo: " + res.getString(1));

			// commit transaction
			stmt.execute("commit tran");

			if (stmt != null)
				stmt.close();
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
