public class Savings {

    private String custnmber;
    private String custmame;
    private String intdeposit;
    private String years;
    private String type;

    public Savings(String custnmber, String custmame, String intdeposit, String years, String type) {
        this.custnmber = custnmber;
        this.custmame = custmame;
        this.intdeposit = intdeposit;
        this.years = years;
        this.type = type;
    }

    public String getCustnmber() {
        return custnmber;
    }

    public void setCustnmber(String custnmber) {
        this.custnmber = custnmber;
    }

    public String getCustmame() {
        return custmame;
    }

    public void setCustmame(String custmame) {
        this.custmame = custmame;
    }

    public String getIntdeposit() {
        return intdeposit;
    }

    public void setIntdeposit(String intdeposit) {
        this.intdeposit = intdeposit;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

