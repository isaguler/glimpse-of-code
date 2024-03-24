package com.isaguler.jfreechartdemo.service;

import com.isaguler.jfreechartdemo.model.Expense;
import com.isaguler.jfreechartdemo.model.Income;
import com.isaguler.jfreechartdemo.repository.ExpenseRepository;
import com.isaguler.jfreechartdemo.repository.IncomeRepository;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
@Slf4j
public class ChartService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public ChartService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public static final String INCOME = "INCOME";
    public static final String EXPENSE = "EXPENSE";
    private static final String FILE_NAME = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "chart.png";

    public byte[] monthlyIncomeChart() {
        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Income income : incomeList) {
            dataset.addValue(income.getAmount().doubleValue(), INCOME, income.getRelatedMonth());
        }

        for (Expense expense : expenseList) {
            dataset.addValue(expense.getAmount().doubleValue(), EXPENSE, expense.getRelatedMonth());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Monthly Income-Expense",
                "Month",
                "Amount",
                dataset);

        File file = new File(FILE_NAME);

        try (OutputStream os = new FileOutputStream(file)) {
            ChartUtils.writeChartAsJPEG(os, chart, 600, 400);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            return inputStream.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
