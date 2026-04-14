import java.util.*;

class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}

abstract class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    abstract void displayRole();
}

class Farmer extends Person {
    double land;
    String crop;
    int qty;

    Farmer(String name, double land, String crop, int qty) throws InvalidDataException {
        super(name);

        if (land <= 0) throw new InvalidDataException("Invalid land");

        this.land = land;
        this.crop = crop;
        this.qty = qty;
    }

    @Override
    void displayRole() {
        System.out.println("👨‍🌾 Farmer: " + name);
    }

    void display() {
        System.out.println(name + " | " + crop + " | Qty: " + qty);
    }
}

class Official extends Person {

    Official(String name) {
        super(name);
    }

    @Override
    void displayRole() {
        System.out.println("🏛️ Official: " + name);
    }

    void view(ArrayList<Farmer> list) {
        if (list.isEmpty()) {
            System.out.println("No data available");
            return;
        }

        for (Farmer f : list) {
            f.display();
        }
    }
}

class Syndicate extends Person {

    Syndicate(String name) {
        super(name);
    }

    @Override
    void displayRole() {
        System.out.println("📊 Syndicate: " + name);
    }

    void analyze(ArrayList<Farmer> list) {
        if (list.isEmpty()) {
            System.out.println("No data to analyze");
            return;
        }

        HashMap<String, Integer> map = new HashMap<>();

        for (Farmer f : list) {
            map.put(f.crop, map.getOrDefault(f.crop, 0) + f.qty);
        }

        System.out.println("\n--- Analysis ---");
        for (String crop : map.keySet()) {
            int total = map.get(crop);
            System.out.println(crop + " = " + total);

            if (total > 100) {
                System.out.println("⚠ Overproduction of " + crop);
            }
        }
    }
}

// Main Class
public class KrushiSetuApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Farmer> farmers = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Farmer");
            System.out.println("2. Official");
            System.out.println("3. Syndicate");
            System.out.println("4. Exit");

            int role = sc.nextInt();

            try {
                switch (role) {

                    
                    case 1 -> {
                        while (true) {
                            System.out.println("\n--- Farmer Menu ---");
                            System.out.println("1. Add Crop Data");
                            System.out.println("2. Back");

                            int ch = sc.nextInt();

                            if (ch == 1) {
                                sc.nextLine();

                                System.out.print("Name: ");
                                String name = sc.nextLine();

                                System.out.print("Land: ");
                                double land = sc.nextDouble();

                                sc.nextLine();
                                System.out.print("Crop: ");
                                String crop = sc.nextLine();

                                System.out.print("Quantity: ");
                                int qty = sc.nextInt();

                                Farmer f = new Farmer(name, land, crop, qty);
                                farmers.add(f);

                                f.displayRole();
                                System.out.println("Data added!");
                            }
                            else if (ch == 2) {
                                break; // back to main menu
                            }
                            else {
                                System.out.println("Invalid choice");
                            }
                        }
                    }

                    case 2 -> {
                        Official o = new Official("Gov Officer");
                        o.displayRole();

                        while (true) {
                            System.out.println("\n--- Official Menu ---");
                            System.out.println("1. View Farmers Data");
                            System.out.println("2. Back");

                            int ch = sc.nextInt();

                            if (ch == 1) {
                                o.view(farmers);
                            }
                            else if (ch == 2) {
                                break;
                            }
                            else {
                                System.out.println("Invalid choice");
                            }
                        }
                    }

                    case 3 -> {
                        Syndicate s = new Syndicate("Market");
                        s.displayRole();

                        while (true) {
                            System.out.println("\n--- Syndicate Menu ---");
                            System.out.println("1. Analyze Supply");
                            System.out.println("2. Back");

                            int ch = sc.nextInt();

                            if (ch == 1) {
                                s.analyze(farmers);
                            }
                            else if (ch == 2) {
                                break;
                            }
                            else {
                                System.out.println("Invalid choice");
                            }
                        }
                    }

                    case 4 -> {
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Invalid choice");
                }

            } catch (InvalidDataException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}