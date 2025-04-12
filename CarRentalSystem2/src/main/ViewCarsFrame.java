package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewCarsFrame extends JFrame {
    public ViewCarsFrame() {
        setTitle("Available Cars");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Brand", "Model", "Year", "Price/Day"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        List<Car> cars = DatabaseManager.getAvailableCars();
        for (Car car : cars) {
            Object[] row = {
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPricePerDay()
            };
            model.addRow(row);
        }
    }
}
