package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;

/**
 * This data transfer object contains financial data about this company.
 */
public class MoneyManagementDTO {
    /**
     * Field that contains sum of all planned profits for all employees in the company.
     */
    private Double plannedProfitSum;
    /**
     * Field that contains sum of all realized profits for all employees in the company.
     */
    private Double realizedProfitSum;
    /**
     * Field that contains sum of all planned costs for all employees in the company.
     */
    private Double plannedCostSum;
    /**
     * Field that contains sum of all realized costs for all employees in the company.
     */
    private Double realizedCostSum;
    /**
     * Field that contains difference of planned profits and planned costs.
     */
    private Double plannedDifference;
    /**
     * Field that contains difference of realized profits and realized costs.
     */
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


