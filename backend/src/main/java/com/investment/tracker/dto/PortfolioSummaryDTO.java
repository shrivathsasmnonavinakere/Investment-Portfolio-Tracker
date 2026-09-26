package com.investment.tracker.dto;

public class PortfolioSummaryDTO {

    private double totalInvestedAmount;
    private double totalCurrentValue;
    private double totalProfitLoss;
    private double totalProfitLossPercentage;
    private String bestPerformingInvestment;
    private String worstPerformingInvestment;

    public double getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    public void setTotalInvestedAmount(double totalInvestedAmount) {
        this.totalInvestedAmount = totalInvestedAmount;
    }

    public double getTotalCurrentValue() {
        return totalCurrentValue;
    }

    public void setTotalCurrentValue(double totalCurrentValue) {
        this.totalCurrentValue = totalCurrentValue;
    }

    public double getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public void setTotalProfitLoss(double totalProfitLoss) {
        this.totalProfitLoss = totalProfitLoss;
    }

    public double getTotalProfitLossPercentage() {
        return totalProfitLossPercentage;
    }

    public void setTotalProfitLossPercentage(double totalProfitLossPercentage) {
        this.totalProfitLossPercentage = totalProfitLossPercentage;
    }

    public String getBestPerformingInvestment() {
        return bestPerformingInvestment;
    }

    public void setBestPerformingInvestment(String bestPerformingInvestment) {
        this.bestPerformingInvestment = bestPerformingInvestment;
    }

    public String getWorstPerformingInvestment() {
        return worstPerformingInvestment;
    }

    public void setWorstPerformingInvestment(String worstPerformingInvestment) {
        this.worstPerformingInvestment = worstPerformingInvestment;
    }
}
