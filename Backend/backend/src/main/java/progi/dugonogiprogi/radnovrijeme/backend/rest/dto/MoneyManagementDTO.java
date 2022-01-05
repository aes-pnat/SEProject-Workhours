package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

/**
 * This data transfer object contains financial data about this company.
 */
public class MoneyManagementDTO {

    /**
     * Total amount of expense/profit.
     */
    private double price;
    /**
     * Calculated difference between expected and real expense/profit.
     */
    private double difference;

    /**
     * Getter for amount of expense/profit.
     *
     * @return amount of money for expense/profit
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for amount of expense/profit.
     *
     * @param price amount of money for expense/profit
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for difference between expected and real expense/profit.
     *
     * @return difference between expected and real expense/profit
     */
    public double getDifference() {
        return difference;
    }

    /**
     * Setter for difference between expected and real expense/profit.
     *
     * @param difference difference between expected and real expense/profit
     */
    public void setDifference(double difference) {
        this.difference = difference;
    }
}


