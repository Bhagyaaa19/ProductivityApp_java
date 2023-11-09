import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class MyJournal {

    public static class JournalEntry {
        private Date date;
        private String entryText;

        public JournalEntry(Date date, String entryText) {
            this.date = date;
            this.entryText = entryText;
        }

        public Date getDate() {
            return date;
        }

        public String getEntryText() {
            return entryText;
        }

		public void setEntryText(String editedText) {
						
		}
    }

    private ArrayList<JournalEntry> entries = new ArrayList<>();
    private JFrame frame;
    private JPanel entryPanel; // Create a separate panel for entries

    public MyJournal() {
        frame = new JFrame("MY JOURNAL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(entryPanel);

        frame.add(mainPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JTextField entryField = new JTextField();
        inputPanel.add(entryField, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Entry");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry(entryField.getText());
                entryField.setText("");
            }
        });
        inputPanel.add(addButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private void addEntry(String text) {
        if (!text.isEmpty()) {
            JournalEntry entry = new JournalEntry(new Date(), text);
            entries.add(entry);
            displayEntries();
        }
    }

    private void displayEntries() {
        entryPanel.removeAll(); // Clear existing entries

        for (int i = 0; i < entries.size(); i++) {
            JournalEntry entry = entries.get(i);

            JTextArea entryText = new JTextArea(entry.getDate() + "\n" + entry.getEntryText());
            entryText.setEditable(false);
            entryPanel.add(entryText);

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new EditButtonListener(i));
            entryPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new DeleteButtonListener(i));
            entryPanel.add(deleteButton);
        }

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyJournal();
            }
        });
    }

    private class EditButtonListener implements ActionListener {
        private int index;

        public EditButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String editedText = JOptionPane.showInputDialog(frame, "Edit the entry:", entries.get(index).getEntryText());
            if (editedText != null) {
                entries.get(index).setEntryText(editedText);
                displayEntries();
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        private int index;

        public DeleteButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this entry?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                entries.remove(index);
                displayEntries();
            }
        }
    }
}
