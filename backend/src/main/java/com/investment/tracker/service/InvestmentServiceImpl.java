package com.investment.tracker.service;

import com.investment.tracker.dto.InvestmentRequestDTO;
import com.investment.tracker.dto.InvestmentResponseDTO;
import com.investment.tracker.dto.PortfolioSummaryDTO;
import com.investment.tracker.entity.Investment;
import com.investment.tracker.exception.InvestmentNotFoundException;
import com.investment.tracker.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public List<InvestmentResponseDTO> getAllInvestments(String type) {
        List<Investment> investments = investmentRepository.findAll();

        if (type != null && !type.isBlank()) {
            investments = investments.stream()
                    .filter(investment -> investment.getType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        return investments.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvestmentResponseDTO addInvestment(InvestmentRequestDTO requestDTO) {
        Investment investment = new Investment();
        investment.setName(requestDTO.getName());
        investment.setType(requestDTO.getType());
        investment.setQuantity(requestDTO.getQuantity());
        investment.setBuyPrice(requestDTO.getBuyPrice());
        investment.setCurrentPrice(requestDTO.getCurrentPrice());

        Investment saved = investmentRepository.save(investment);
        return mapToResponseDTO(saved);
    }

    @Override
    public InvestmentResponseDTO updateInvestment(Long id, InvestmentRequestDTO requestDTO) {
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(() -> new InvestmentNotFoundException(id));

        investment.setName(requestDTO.getName());
        investment.setType(requestDTO.getType());
        investment.setQuantity(requestDTO.getQuantity());
        investment.setBuyPrice(requestDTO.getBuyPrice());
        investment.setCurrentPrice(requestDTO.getCurrentPrice());

        Investment updated = investmentRepository.save(investment);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteInvestment(Long id) {
        if (!investmentRepository.existsById(id)) {
            throw new InvestmentNotFoundException(id);
        }
        investmentRepository.deleteById(id);
    }

    @Override
    public PortfolioSummaryDTO getPortfolioSummary() {
        List<Investment> investments = investmentRepository.findAll();

        double totalInvested = 0;
        double totalCurrent = 0;

        for (Investment investment : investments) {
            totalInvested += investment.getBuyPrice() * investment.getQuantity();
            totalCurrent += investment.getCurrentPrice() * investment.getQuantity();
        }

        double totalProfitLoss = totalCurrent - totalInvested;
        double totalProfitLossPercentage = totalInvested == 0 ? 0 : (totalProfitLoss / totalInvested) * 100;

        String best = investments.stream()
                .max(Comparator.comparingDouble(this::calculateProfitLossPercentage))
                .map(Investment::getName)
                .orElse("No data");

        String worst = investments.stream()
                .min(Comparator.comparingDouble(this::calculateProfitLossPercentage))
                .map(Investment::getName)
                .orElse("No data");

        PortfolioSummaryDTO summary = new PortfolioSummaryDTO();
        summary.setTotalInvestedAmount(totalInvested);
        summary.setTotalCurrentValue(totalCurrent);
        summary.setTotalProfitLoss(totalProfitLoss);
        summary.setTotalProfitLossPercentage(totalProfitLossPercentage);
        summary.setBestPerformingInvestment(best);
        summary.setWorstPerformingInvestment(worst);

        return summary;
    }

    private double calculateProfitLossPercentage(Investment investment) {
        if (investment.getBuyPrice() == 0) {
            return 0;
        }
        return ((investment.getCurrentPrice() - investment.getBuyPrice()) / investment.getBuyPrice()) * 100;
    }

    private InvestmentResponseDTO mapToResponseDTO(Investment investment) {
        InvestmentResponseDTO dto = new InvestmentResponseDTO();
        dto.setId(investment.getId());
        dto.setName(investment.getName());
        dto.setType(investment.getType());
        dto.setQuantity(investment.getQuantity());
        dto.setBuyPrice(investment.getBuyPrice());
        dto.setCurrentPrice(investment.getCurrentPrice());

        double profitLoss = (investment.getCurrentPrice() - investment.getBuyPrice()) * investment.getQuantity();
        dto.setProfitLoss(profitLoss);
        dto.setProfitLossPercentage(calculateProfitLossPercentage(investment));

        return dto;
    }
}
