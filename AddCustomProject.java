import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerGUI {
    private JFrame frame;
    private JTextField taskNameField;
    private JTextField startDateField;
    private JTextField dueDateField;
    private JTextArea commentArea;
    private JComboBox<String> priorityComboBox;
    private JComboBox<String> statusComboBox;
    private JTextField reminderTimeField;
    private JTextArea attachmentsArea;
    private List<Project> projects;

    public ProjectManagerGUI() {
        frame = new JFrame("Customer Project Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        projects = new ArrayList<>();

        JLabel titleLabel = new JLabel("Add Customer Project");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel taskLabel = new JLabel("Task Name:");
        taskNameField = new JTextField(20);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateField = new JTextField(20);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateField = new JTextField(20);

        JLabel commentLabel = new JLabel("Comment:");
        commentArea = new JTextArea(5, 20);
        commentArea.setLineWrap(true);

        JLabel priorityLabel = new JLabel("Priority:");
        String[] priorities = {"High", "Medium", "Low"};
        priorityComboBox = new JComboBox<>(priorities);

        JLabel statusLabel = new JLabel("Status:");
        String[] statuses = {"Not Started", "In Progress", "Completed"};
        statusComboBox = new JComboBox<>(statuses);

        JLabel reminderTimeLabel = new JLabel("Reminder Time (in days):");
        reminderTimeField = new JTextField(20);

        JLabel attachmentsLabel = new JLabel("Attachments (comma-separated):");
        attachmentsArea = new JTextArea(3, 20);
        attachmentsArea.setLineWrap(true);

        JButton addButton = new JButton("Add Project");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject();
            }
        });

        JButton displayButton = new JButton("Display Projects");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayProjects();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));
        panel.add(titleLabel);
        panel.add(new JLabel(""));
        panel.add(taskLabel);
        panel.add(taskNameField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(dueDateLabel);
        panel.add(dueDateField);
        panel.add(commentLabel);
        panel.add(new JScrollPane(commentArea));
        panel.add(priorityLabel);
        panel.add(priorityComboBox);
        panel.add(statusLabel);
        panel.add(statusComboBox);
        panel.add(reminderTimeLabel);
        panel.add(reminderTimeField);
        panel.add(attachmentsLabel);
        panel.add(new JScrollPane(attachmentsArea));
        panel.add(new JLabel(""));
        panel.add(addButton);
        panel.add(displayButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addProject() {
        String taskName = taskNameField.getText();
        String startDate = startDateField.getText();
        String dueDate = dueDateField.getText();
        String comment = commentArea.getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        String status = (String) statusComboBox.getSelectedItem();
        int reminderTime = Integer.parseInt(reminderTimeField.getText());
        String attachments = attachmentsArea.getText();

        Project project = new Project(taskName, startDate, dueDate, comment, priority, status, reminderTime, attachments);
        projects.add(project);

        JOptionPane.showMessageDialog(frame, "Project added successfully!");
    }

    private void displayProjects() {
        StringBuilder projectList = new StringBuilder("Project List:\n");

        for (Project project : projects) {
            projectList.append("Task Name: ").append(project.getTaskName()).append("\n");
            projectList.append("Start Date: ").append(project.getStartDate()).append("\n");
            projectList.append("Due Date: ").append(project.getDueDate()).append("\n");
            // Add more project details here as needed
            projectList.append("\n");
        }

        JTextArea projectListArea = new JTextArea(10, 40);
        projectListArea.setText(projectList.toString());
        projectListArea.setEditable(false);

        JOptionPane.showMessageDialog(frame, new JScrollPane(projectListArea), "Project List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProjectManagerGUI();
            }
        });
    }

    public static class Project {
        private String taskName;
        private String startDate;
        private String dueDate;
        private String comment;
        private String priority;
        private String status;
        private int reminderTime;
        private String attachments;

        public Project(String taskName, String startDate, String dueDate, String comment, String priority, String status, int reminderTime, String attachments) {
            this.taskName = taskName;
            this.startDate = startDate;
            this.dueDate = dueDate;
            this.comment = comment;
            this.priority = priority;
            this.status = status;
            this.reminderTime = reminderTime;
            this.attachments = attachments;
        }

        // Getters and setters for the project attributes
        public String getTaskName() {
            return taskName;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getDueDate() {
            return dueDate;
        }
    }
}