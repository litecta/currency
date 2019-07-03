import javax.swing.*;

public class Main {

    public static void main(String[] args){

        Database.makeDBConnection();
        Currency.getData(); // iskvieciamas getData funkcijos vykdymas kad butu paimti duomenys is SQL ir sudedami masyva

        /* Sukuriame frame objekta ir parodome varotojui */

        JFrame frame = new JFrame("Currency");
        frame.setContentPane(new Currency().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);//nurodo frame dydi
        frame.setVisible(true);









    }





    }

