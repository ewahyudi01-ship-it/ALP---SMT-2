public class HealthReport {
    private User user;

    public HealthReport(User user) {
        this.user = user;
    }

    public double getTotalCaloriesFromAllTransactions() {
        double total = 0;
        for (Purchase p : user.historiPembelian) {
            FoodItem food = p.getFoodItem();
            total += food.getCalories() * p.getQuantity();
        }
        return total;
    }

    public double getTotalProteinFromAllTransactions() {
        double total = 0;
        for (Purchase p : user.historiPembelian) {
            FoodItem food = p.getFoodItem();
            total += food.getProtein() * p.getQuantity();
        }
        return total;
    }

    public double getTotalSugarFromAllTransactions() {
        double total = 0;
        for (Purchase p : user.historiPembelian) {
            FoodItem food = p.getFoodItem();
            total += food.getSugarLvl() * p.getQuantity();
        }
        return total;
    }

    // Recommended daily values based on body weight
    public double getRecommendedCalories() {
        // General formula: 30 kcal per kg body weight
        return user.beratBadan * 30;
    }

    public double getRecommendedProtein() {
        // General formula: 0.8g protein per kg body weight
        return user.beratBadan * 0.8;
    }

    public double getRecommendedMaxSugar() {
        // WHO recommends max 25g sugar per day (same for all)
        return 25;
    }

    public String getCalorieStatus() {
        double consumed = getTotalCaloriesFromAllTransactions();
        double recommended = getRecommendedCalories();
        if (consumed > recommended * 1.2) return "OVER (exceeds daily limit)";
        else if (consumed < recommended * 0.5) return "LOW (below daily need)";
        else return "NORMAL";
    }

    public String getProteinStatus() {
        double consumed = getTotalProteinFromAllTransactions();
        double recommended = getRecommendedProtein();
        if (consumed >= recommended) return "SUFFICIENT";
        else return "LOW (below daily need)";
    }

    public String getSugarStatus() {
        double consumed = getTotalSugarFromAllTransactions();
        double maxSugar = getRecommendedMaxSugar();
        if (consumed > maxSugar) return "HIGH (exceeds safe limit)";
        else return "NORMAL";
    }

    public void displayReport() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("              ♥  HEALTH REPORT - " + user.username + "  ♥     ");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("Body weight      : " + user.beratBadan + " kg");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.printf("%-25s %-15s %-15s%n", "Nutrition", "Consumed", "Recommended");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.printf("%-25s %-15.1f %-15.1f%n",
                "Calories (kcal):", getTotalCaloriesFromAllTransactions(), getRecommendedCalories());
        System.out.printf("%-25s %-15.1f %-15.1f%n",
                "Protein (g):", getTotalProteinFromAllTransactions(), getRecommendedProtein());
        System.out.printf("%-25s %-15.1f %-15.1f%n",
                "Sugar (g):", getTotalSugarFromAllTransactions(), getRecommendedMaxSugar());
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("Health Status:");
        System.out.println("  Calorie intake : " + getCalorieStatus());
        System.out.println("  Protein intake : " + getProteinStatus());
        System.out.println("  Sugar level    : " + getSugarStatus());
        System.out.println("──────────────────────────────────────────────────────────────");
        if (user.historiPembelian.isEmpty()) {
            System.out.println("  No purchase data yet. Buy some food to track your nutrition!");
        } else {
            System.out.println("  Based on " + user.historiPembelian.size() + " purchase(s) recorded.");
        }
        System.out.println("══════════════════════════════════════════════════════════════");
    }

    /**
     * Checks if the given food is safe/suitable for the user based on their body condition.
     * Returns null if safe, or a reason string if unsafe.
     */
    public String checkFoodSafety(FoodItem food, int quantity) {
        double totalCalAfter = getTotalCaloriesFromAllTransactions() + food.getCalories() * quantity;
        double totalSugarAfter = getTotalSugarFromAllTransactions() + food.getSugarLvl() * quantity;

        double maxCalories = getRecommendedCalories() * 1.5; // batas: 150% daily
        double maxSugar = getRecommendedMaxSugar() * 2;      // batas: 2x WHO limit

        String errorMessage = "";

        if (totalCalAfter > maxCalories) {
            errorMessage = "- Calorie limit exceeded! Adding this item would bring your total calories to "
                    + String.format("%.1f", totalCalAfter) + " kcal, which is unsafe for your body weight ("
                    + user.beratBadan + " kg). \nMax allowed: " + String.format("%.1f", maxCalories) + " kcal.";
        }
        if (totalSugarAfter > maxSugar) {
            errorMessage = "\n- Sugar level too high! Adding this item would bring your total sugar to "
                    + String.format("%.1f", totalSugarAfter) + "g, which is unsafe. Max allowed: " + String.format("%.1f", maxSugar) + "g.";
        }
        if(!errorMessage.equals("")) {
            return errorMessage;
        } else {
            return null; // safe
        }
    }
}
