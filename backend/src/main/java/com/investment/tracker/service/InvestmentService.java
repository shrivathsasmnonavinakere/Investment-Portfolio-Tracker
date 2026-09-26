package com.investment.tracker.service;

import com.investment.tracker.dto.InvestmentRequestDTO;
import com.investment.tracker.dto.InvestmentResponseDTO;
import com.investment.tracker.dto.PortfolioSummaryDTO;

import java.util.List;

public interface InvestmentService {

    List<InvestmentResponseDTO> getAllInvestments(String type);

    InvestmentResponseDTO addInvestment(InvestmentRequestDTO requestDTO);

    InvestmentResponseDTO updateInvestment(Long id, InvestmentRequestDTO requestDTO);

    void deleteInvestment(Long id);

    PortfolioSummaryDTO getPortfolioSummary();
}
