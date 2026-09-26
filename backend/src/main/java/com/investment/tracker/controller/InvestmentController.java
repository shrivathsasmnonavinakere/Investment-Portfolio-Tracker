package com.investment.tracker.controller;

import com.investment.tracker.dto.InvestmentRequestDTO;
import com.investment.tracker.dto.InvestmentResponseDTO;
import com.investment.tracker.dto.PortfolioSummaryDTO;
import com.investment.tracker.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
@CrossOrigin(origins = "*")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping
    public List<InvestmentResponseDTO> getAllInvestments(
            @RequestParam(required = false) String type) {
        return investmentService.getAllInvestments(type);
    }

    @GetMapping("/summary")
    public PortfolioSummaryDTO getPortfolioSummary() {
        return investmentService.getPortfolioSummary();
    }

    @PostMapping
    public InvestmentResponseDTO addInvestment(@Valid @RequestBody InvestmentRequestDTO requestDTO) {
        return investmentService.addInvestment(requestDTO);
    }

    @PutMapping("/{id}")
    public InvestmentResponseDTO updateInvestment(
            @PathVariable Long id,
            @Valid @RequestBody InvestmentRequestDTO requestDTO) {
        return investmentService.updateInvestment(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
    }
}
