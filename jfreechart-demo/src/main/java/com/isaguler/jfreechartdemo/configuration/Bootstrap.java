package com.isaguler.jfreechartdemo.configuration;

import com.isaguler.jfreechartdemo.model.Expense;
import com.isaguler.jfreechartdemo.model.Income;
import com.isaguler.jfreechartdemo.repository.ExpenseRepository;
import com.isaguler.jfreechartdemo.repository.IncomeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Component
public class Bootstrap {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public Bootstrap(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    @PostConstruct
    void init() {
        Income income1 = new Income();
        income1.setAmount(new BigDecimal(100));
        income1.setRelatedMonth(Month.JANUARY);

        Income income2 = new Income();
        income2.setAmount(new BigDecimal(120));
        income2.setRelatedMonth(Month.FEBRUARY);

        Income income3 = new Income();
        income3.setAmount(new BigDecimal(90));
        income3.setRelatedMonth(Month.MARCH);

        incomeRepository.saveAll(List.of(income1, income2, income3));

        Expense expense1 = new Expense();
        expense1.setAmount(new BigDecimal(20));
        expense1.setRelatedMonth(Month.JANUARY);

        Expense expense2 = new Expense();
        expense2.setAmount(new BigDecimal(80));
        expense2.setRelatedMonth(Month.FEBRUARY);

        Expense expense3 = new Expense();
        expense3.setAmount(new BigDecimal(40));
        expense3.setRelatedMonth(Month.MARCH);

        expenseRepository.saveAll(List.of(expense1, expense2, expense3));
    }
}
