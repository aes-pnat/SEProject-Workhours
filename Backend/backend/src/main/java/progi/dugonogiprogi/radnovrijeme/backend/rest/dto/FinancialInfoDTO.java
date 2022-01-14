package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class FinancialInfoDTO {

    Double realizedRevenue;
    Double realizedCost;
    Double realizedProfit;

    public Double getRealizedRevenue() {
        return realizedRevenue;
    }

    public void setRealizedRevenue(Double realizedRevenue) {
        this.realizedRevenue = realizedRevenue;
    }

    public Double getRealizedCost() {
        return realizedCost;
    }

    public void setRealizedCost(Double realizedCost) {
        this.realizedCost = realizedCost;
    }

    public Double getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(Double realizedProfit) {
        this.realizedProfit = realizedProfit;
    }
}
