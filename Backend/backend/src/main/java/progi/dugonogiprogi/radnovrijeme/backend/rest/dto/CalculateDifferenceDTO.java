package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class CalculateDifferenceDTO {
    Double revenueDifference;
    Double costDifference;
    Double profitDifference;

    public Double getRevenueDifference() {
        return revenueDifference;
    }

    public void setRevenueDifference(Double revenueDifference) {
        this.revenueDifference = revenueDifference;
    }

    public Double getCostDifference() {
        return costDifference;
    }

    public void setCostDifference(Double costDifference) {
        this.costDifference = costDifference;
    }

    public Double getProfitDifference() {
        return profitDifference;
    }

    public void setProfitDifference(Double profitDifference) {
        this.profitDifference = profitDifference;
    }
}
