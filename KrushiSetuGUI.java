import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Farmer class
class Farmer {
    String name, crop;
    double land;
    int qty;

    Farmer(String name, double land, String crop, int qty) {
        this.name = name;
        this.land = land;
        this.crop = crop;
        this.qty = qty;
    }

    public String toString() {
        return name + " | " + crop + " | Qty: " + qty;
    }
}

public class KrushiSetuGUI {

    static ArrayList<Farmer> farmers = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Krushi Setu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel title = new JLabel("Select Role");
        JButton farmerBtn = new JButton("Farmer");
        JButton officialBtn = new JButton("Official");
        JButton syndicateBtn = new JButton("Syndicate");

        frame.add(title);
        frame.add(farmerBtn);
        frame.add(officialBtn);
        frame.add(syndicateBtn);

        // Farmer Button
        farmerBtn.addActionListener(e -> openFarmerWindow());

        // Official Button
        officialBtn.addActionListener(e -> openOfficialWindow());

        // Syndicate Button
        syndicateBtn.addActionListener(e -> openSyndicateWindow());

        frame.setVisible(true);
    }

    // 👨‍🌾 Farmer Window
    static void openFarmerWindow() {
        JFrame f = new JFrame("Farmer");
        f.setSize(400, 300);
        f.setLayout(new GridLayout(5, 2));

        JTextField name = new JTextField();
        JTextField land = new JTextField();
        JTextField crop = new JTextField();
        JTextField qty = new JTextField();

        JButton submit = new JButton("Submit");

        f.add(new JLabel("Name:"));
        f.add(name);
        f.add(new JLabel("Land:"));
        f.add(land);
        f.add(new JLabel("Crop:"));
        f.add(crop);
        f.add(new JLabel("Quantity:"));
        f.add(qty);
        f.add(submit);

        submit.addActionListener(e -> {
            try {
                String n = name.getText();
                double l = Double.parseDouble(land.getText());
                String c = crop.getText();
                int q = Integer.parseInt(qty.getText());

                farmers.add(new Farmer(n, l, c, q));

                JOptionPane.showMessageDialog(f, "Data Added!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid Input!");
            }
        });

        f.setVisible(true);
    }

    // 🏛️ Official Window
    static void openOfficialWindow() {
        JFrame f = new JFrame("Official");
        f.setSize(400, 300);

        JTextArea area = new JTextArea();

        if (farmers.isEmpty()) {
            area.setText("No Data Available");
        } else {
            for (Farmer farmer : farmers) {
                area.append(farmer.toString() + "\n");
            }
        }

        f.add(new JScrollPane(area));
        f.setVisible(true);
    }

    // 📊 Syndicate Window
    static void openSyndicateWindow() {
        JFrame f = new JFrame("Syndicate");
        f.setSize(400, 300);

        JTextArea area = new JTextArea();
        HashMap<String, Integer> map = new HashMap<>();

        for (Farmer farmer : farmers) {
            map.put(farmer.crop, map.getOrDefault(farmer.crop, 0) + farmer.qty);
        }

        for (String crop : map.keySet()) {
            int total = map.get(crop);
            area.append(crop + " = " + total + "\n");

            if (total > 100) {
                area.append("⚠ Overproduction of " + crop + "\n");
            }
        }

        if (map.isEmpty()) {
            area.setText("No Data Available");
        }

        f.add(new JScrollPane(area));
        f.setVisible(true);
    }
}