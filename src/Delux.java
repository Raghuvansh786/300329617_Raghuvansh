public class Delux  extends Savings implements Compound_Interest{


    public Delux(String custnmber, String custmame, String intdeposit, String years, String type) {
        super(custnmber, custmame, intdeposit, years, type);
    }

    @Override
    public void GenerateTable()
    {
        String custmame = getCustmame();
        String custnum = getCustnmber();
        String Deposit = getIntdeposit();
        String years = getYears();

        Double interest = Double.parseDouble(Deposit)*.15 ;

        double totalInterest = interest*Integer.parseInt(years);

        Double finalamt = Double.parseDouble(Deposit) + totalInterest;

        String[][] array2 = new String[][4];



        String[] colums = {"Year", "Starting", "Interest", "Ending Value"};
    }
}
