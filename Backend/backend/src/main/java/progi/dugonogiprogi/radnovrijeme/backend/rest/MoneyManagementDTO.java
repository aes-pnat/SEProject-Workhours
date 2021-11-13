package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;

public class MoneyManagementDTO {
    private Double plannedProfitSum;
    private Double realizedProfitSum;
    private Double plannedCostSum;
    private Double realizedCostSum;
    private Double plannedDifference;
    private Double realizedDifference;

    public MoneyManagementDTO(List<Task> taskList) {
        this.plannedProfitSum = taskList.stream().mapToDouble(t -> t.getPlannedProfit()).sum();
        this.realizedProfitSum = taskList.stream().mapToDouble(t -> t.getRealizedProfit()).sum();
        this.plannedCostSum = taskList.stream().mapToDouble(t -> t.getPlannedCost()).sum();
        this.realizedCostSum = taskList.stream().mapToDouble(t -> t.getRealizedCost()).sum();
        this.plannedDifference = plannedProfitSum - plannedCostSum;
        this.realizedDifference = realizedProfitSum - realizedCostSum;
    }

    public Double getPlannedProfitSum() {
        return plannedProfitSum;
    }

    public void setPlannedProfitSum(Double plannedProfitSum) {
        this.plannedProfitSum = plannedProfitSum;
    }

    public Double getRealizedProfitSum() {
        return realizedProfitSum;
    }

    public void setRealizedProfitSum(Double realizedProfitSum) {
        this.realizedProfitSum = realizedProfitSum;
    }

    public Double getPlannedCostSum() {
        return plannedCostSum;
    }

    public void setPlannedCostSum(Double plannedCostSum) {
        this.plannedCostSum = plannedCostSum;
    }

    public Double getRealizedCostSum() {
        return realizedCostSum;
    }

    public void setRealizedCostSum(Double realizedCostSum) {
        this.realizedCostSum = realizedCostSum;
    }

    public Double getPlannedDifference() {
        return plannedDifference;
    }

    public void setPlannedDifference(Double plannedDifference) {
        this.plannedDifference = plannedDifference;
    }

    public Double getRealizedDifference() {
        return realizedDifference;
    }

    public void setRealizedDifference(Double realizedDifference) {
        this.realizedDifference = realizedDifference;
    }
}


