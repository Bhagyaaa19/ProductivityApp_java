package EX;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;

public class tracker extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Map<String, List<Expense>> expensesByMonth;
    private JTextField monthTextField, descriptionTextField, amountTextField, yearTextField, dateTextField, timeTextField;
    private JTextArea resultTextArea;

    public tracker(Map<String, List<Expense>> expensesByMonth) {
        this.expensesByMonth = expensesByMonth;
        setTitle("Monthly Expenses Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel descriptionLabel = new JLabel("Expense Description:");
        JLabel amountLabel = new JLabel("Expense Amount:");
        JLabel yearLabel = new JLabel("Year:");
        JLabel dateLabel = new JLabel("Expense Date (MM/DD):");
        JLabel timeLabel = new JLabel("Expense Time (HH:MM):");

        monthTextField = new JTextField(15);
        descriptionTextField = new JTextField(15);
        amountTextField = new JTextField(15);
        yearTextField = new JTextField(15);
        dateTextField = new JTextField(15);
        timeTextField = new JTextField(15);

        JButton addExpenseButton = new JButton("Add Expense");
        JButton viewExpensesButton = new JButton("View Expenses");
        JButton exitButton = new JButton("Exit");
        JButton editExpenseButton = new JButton("Edit Expense");
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        
        inputPanel.add(yearLabel);
        inputPanel.add(yearTextField);
        inputPanel.add(monthLabel);
        inputPanel.add(monthTextField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateTextField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeTextField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionTextField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountTextField);
        inputPanel.add(addExpenseButton);
        inputPanel.add(viewExpensesButton);
        inputPanel.add(exitButton);
        inputPanel.add(editExpenseButton);
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        viewExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpenses();
            }
        });
    
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the current JFrame
        }
    });

    editExpenseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editExpense();
        }
    });
}
    private void addExpense() {
        String month = monthTextField.getText();
        String description = descriptionTextField.getText();
        double amount = Double.parseDouble(amountTextField.getText());
        int year = Integer.parseInt(yearTextField.getText());
        String date = dateTextField.getText();
        String time = timeTextField.getText();

        Expense expense = new Expense(description, amount, year, date, time);

        if (!expensesByMonth.containsKey(month)) {
            expensesByMonth.put(month, new ArrayList<>());
        }

        expensesByMonth.get(month).add(expense);
        resultTextArea.setText("Expense added successfully for " + month);
    }

    private void viewExpenses() {
        String viewMonth = monthTextField.getText();

        if (expensesByMonth.containsKey(viewMonth)) {
            List<Expense> expenses = expensesByMonth.get(viewMonth);
            if (expenses.isEmpty()) {
                resultTextArea.setText("No expenses for " + viewMonth + " to display.");
            } else {
                StringBuilder sb = new StringBuilder("Expenses for " + viewMonth + ":\n");
                for (int i = 0; i < expenses.size(); i++) {
                    Expense exp = expenses.get(i);
                    sb.append((i + 1)).append(". ").append(exp.getDescription()).append(": $")
                            .append(exp.getAmount()).append(", Year: ").append(exp.getYear())
                            .append(", Date: ").append(exp.getDate()).append(", Time: ").append(exp.getTime()).append("\n");
                }
                resultTextArea.setText(sb.toString());
            }
        } else {
            resultTextArea.setText("No expenses recorded for " + viewMonth);
        }
        private void editExpense() {
            
        }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Map<String, List<Expense>> expensesByMonth = new HashMap<>();
                tracker frame = new tracker(expensesByMonth);
                frame.setVisible(true);
            }
        });
    }
}
