import javax.swing.table.DefaultTableModel;

public class Regular extends Savings implements Compound_Interest{
     public Regular(String custnmber, String custmame, String intdeposit, String years, String type) {
        super(custnmber, custmame, intdeposit, years, type);
    }

    @Override
    public void GenerateTable() {
        String custmame = getCustmame();
        String custnum = getCustnmber();
        String Deposit = getIntdeposit();
        String years = getYears();

        Double interest = Double.parseDouble(Deposit)*.10 ;

        double totalInterest = interest*Integer.parseInt(years);

        Double finalamt = Double.parseDouble(Deposit) + totalInterest;

        String[] colums = {"Year", "Starting", "Interest", "Ending Value"};

        String[][] arrays = new String[][];


        DefaultTableModel model = new DefaultTableModel(arrays,colums);

    }
}
