import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.math.*;

public class Currency extends Database{

    private static  Map<String, Double> currencyRates; // sukuriamas statinis kintamasis pagal kuri bus kuriamas tolimesnis masyvas

    protected JPanel panel1;
    private JButton convertButton;
    private JComboBox comboBox1;
    private JTextField setAmount;
    private JTextField getAmount;
    private JTextField rate;
    private JLabel exchangeRate;
    private JLabel fromAmount;
    private JComboBox comboBox2;
    private JLabel setCurrency;
    private JLabel getCurrency;
    private JLabel toAmount;

    private Double selectedRate;

    public static void getData() {

        currencyRates = new HashMap<>();// sukuriamas tuscias currencyRates masyvas

        try {
            String selectQueryStatement = "SELECT * from currencies";
            dbPrepareStatement = dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = dbPrepareStatement.executeQuery();

            while (results.next()) {   /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                String Symbol = results.getString("symbol");
                Double dbRate = results.getDouble("dbRate");
                Currency.currencyRates.put(Symbol, dbRate);// ikeliami duomenys i currencyRates masyva
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Currency() {

        //is duomenu masyvo i combobox sukeliami valiutu symboliai
        for (Map.Entry<String, Double> currency : currencyRates.entrySet()) {
            comboBox1.addItem(currency.getKey());
        }
        for (Map.Entry<String, Double> currency : currencyRates.entrySet()) {
            comboBox2.addItem(currency.getKey());
        }

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object setCurrency = comboBox1.getSelectedItem();
                Object getCurrency = comboBox2.getSelectedItem();

                selectedRate = currencyRates.get(getCurrency.toString()) / currencyRates.get(setCurrency.toString());

                // Assign value to BigDecimal object b1, b1 is rounded using m
                BigDecimal b1 = new BigDecimal(selectedRate);
                MathContext m = new MathContext(4); // 4 precision
                BigDecimal b2 = b1.round(m);

                rate.setText(b2.toString());
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double convert = Double.parseDouble(setAmount.getText())*Double.parseDouble(rate.getText());

                String convertedAmount = Double.toString(convert);
                getAmount.setText(convertedAmount);
                UsersInfo temp = new UsersInfo(Integer.parseInt(setAmount.getText()), comboBox2.getSelectedItem().toString());// sukuriamas naujas objektas, kai paspaudziamas mygtukas
            }
        });

    }

}
