// Chat-GPT Generated Code

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MainUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton;

    public MainUI() {
        setTitle("Top 10 People");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);
        loadButton = new JButton("Load Top 10 People");

        loadButton.addActionListener(e -> loadPeople());

        setLayout(new BorderLayout());
        add(loadButton, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadPeople() {
        String connectionURL =
            "jdbc:sqlserver://localhost:8088;"
            + "databaseName=AdventureWorks2022;"
            + "user=sa;"
            + "password=Cheeselike2@;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

        // Change this query to your real table/column names
        String sql = "SELECT TOP 10 * FROM Person.Person";

        try (
            Connection con = DriverManager.getConnection(connectionURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            model.setRowCount(0);
            model.setColumnCount(0);

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(meta.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI().setVisible(true);
        });
    }
}