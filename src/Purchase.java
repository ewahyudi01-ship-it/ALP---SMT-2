import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Tambahkan library JSON, misalnya Jackson atau org.json
// Contoh di bawah pakai org.json:
import org.json.JSONObject;


public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public long waktuPesan = 0;
    public int totalWaktu;

    public Purchase(User user, FoodItem foodItem, int quantity) {
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;

        if (foodItem instanceof FoodMasak) {
            this.totalWaktu = ((FoodMasak) foodItem).getWaktuBuat() * quantity;
        } else {
            this.totalWaktu = 0;
        }
    }

    public double getCalculateTotal() {
        return foodItem.getHarga() * quantity;
    }  // encapsulation , method: getter

    public void printReceipt(Scanner scanner) {

        System.out.println("Transaction completed! you bought " + foodItem.foodName + " for " + this.quantity);
        System.out.println("Cost: " + getCalculateTotal());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Do you want your receipt (PDF)?");
        System.out.println("1.Yes\n2.No");
        System.out.print("Choice: ");
        try {
            int n = scanner.nextInt();
            if (n == 1) {
                System.out.println("Wait for your receipt to generate...");
                try {
                    // 1) CREATE JOB
                    URL url = new URL("https://apdf.io/api/pdf/file/create");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Authorization", "Bearer 18CxweWKjZ9ZPcsBoEG5Aob16h5WtZsh1b1icIua9e358837");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                    String notaHtml = "<html><head>"
                            + "<script src='https://cdn.tailwindcss.com'></script>"
                            + "</head>"
                            + "<body class='bg-gray-100 flex justify-center items-center min-h-screen p-5 font-mono'>" // background luar

                            + "<div class='w-120 bg-white p-6 text-left'>"  // background dalam
                            + "<div class='border-t border-dashed border-gray-400 my-30'></div>"  // bikin garis dari kiri ke kanan

                            + "<h2 class='text-center text-6xl font-bold tracking-wide'>SMART CANTEEN RECEIPT</h2>"
                            + "<p class='text-center text-4xl text-gray-500 mb-4'>Surabaya, Indonesia</p>"

                            + "<div class='border-t border-dashed border-gray-400'></div>"

                            + "<div class='space-y-2 text-2xl'>"
                            + "  <p><span class='font-bold'>Product:</span> " + getFoodItem().getFoodName() + "</p>"
                            + "  <p><span class='font-bold'>Quantity:</span> " + getQuantity() + "</p>"
                            + "</div>"

                            + "<div class='border-t border-dashed border-gray-400 my-3'></div>"

                            + "<p class='text-2xl font-black text-gray-800'>TOTAL: Rp " + getCalculateTotal() + "</p>"

                            + "<div class='border-t border-dashed border-gray-400 my-3'></div>"

                            + "<p class='text-center text-1xl text-gray-600 italic mt-4'>Thanks for shopping by! :)</p>"
                            + "<p class='text-center text-1xl text-gray-400'>Please come again</p>"

                            +"<p class='text-center text-gray'> " +
                            "⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⣻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠃⠀⠀\n" +
                            "⠀⠀⠀⣳⣧⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣾⣻⡇⠀⠀⠀\n" +
                            "⠀⠀⠀⢘⣿⣽⣿⣿⣷⣷⣄⣠⢰⣿⣷⣦⣢⣶⡶⣶⣇⣴⡷⣻⣿⣿⣿⣿⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⡠⠀⠀⠀\n" +
                            "⠀⠀⢨⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣇⠀⠀⠀\n" +
                            "⠀⠀⠈⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠹⣿⣿⣿⣿⣿⠟⠁⠀⠀⠹⣿⣿⣿⣿⠇⠀⠀⠀\n" +
                            "⠀⠀⠀⣽⣿⣿⣿⡇⠀⠀⠀⢼⣿⠀⢿⣿⣿⣿⣿⠀⣾⣷⠀⠀⢿⣿⣿⣿⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢹⣿⣿⣿⣷⣀⠀⠀⠈⠋⢀⣿⣿⣿⣿⣿⡀⠙⠋⠀⢀⣾⣿⣿⣿⠀⠀⠀⠀\n" +
                            "⢀⣀⣀⣀⣿⣿⣿⣿⣿⣷⣶⣶⣶⣿⣿⣿⣿⣾⣿⣷⣦⣤⣴⣿⣿⣿⣿⣤⠤⢤⣤⡄\n" +
                            "⠈⠉⠉⢉⣙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣀⣀⣀⡀⠀\n" +
                            "⠐⠚⠋⠉⢀⣬⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣥⣀⡀⠈⠀⠈⠛\n" +
                            "⠀⠀⠴⠚⠉⠀⠀⠀⠉⠛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠋⠁⠀⠀⠀⠉⠛⠢⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⢀⣤⣤⣄⢀⣶⠏⢷⡄\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⢽⠀⠀⠀⠀⠀⠀⠀⣿\n" +
                            "⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠈⣆⠀⠀⠀⠀⠀⣸⠁\n" +
                            "⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠑⣄⠀⠀⡾⠀⠀\n" +
                            "⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠹⡜⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀ </p>" +
                            "</div>" +
                            "</body></html>";

                    String body = "html=" + URLEncoder.encode(notaHtml, StandardCharsets.UTF_8);

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(body.getBytes(StandardCharsets.UTF_8));
                    }

                    String createResponse;
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        createResponse = sb.toString();
                    }

                    JSONObject createJson = new JSONObject(createResponse);
                    String jobId = createJson.getString("job_id");

                    // 2) CHECK STATUS
                    String fileUrl = null;
                    while (true) {
                        URL statusUrl = new URL("https://apdf.io/api/job/status/check");
                        HttpURLConnection statusConn = (HttpURLConnection) statusUrl.openConnection();
                        statusConn.setRequestMethod("POST");
                        statusConn.setDoOutput(true);
                        statusConn.setRequestProperty("Authorization", "Bearer 18CxweWKjZ9ZPcsBoEG5Aob16h5WtZsh1b1icIua9e358837");
                        statusConn.setRequestProperty("Accept", "application/json");
                        statusConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                        String statusBody = "id=" + URLEncoder.encode(jobId, StandardCharsets.UTF_8);
                        try (OutputStream os = statusConn.getOutputStream()) {
                            os.write(statusBody.getBytes(StandardCharsets.UTF_8));
                        }

                        String statusResponse;
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(statusConn.getInputStream(), StandardCharsets.UTF_8))) {
                            statusResponse = br.lines().reduce("", (a, b) -> a + b);
                        }

                        JSONObject statusJson = new JSONObject(statusResponse);
                        String status = statusJson.getString("status");

                        if ("successful".equalsIgnoreCase(status)) {
                            fileUrl = statusJson.getJSONObject("result").getString("file");
                            break;
                        }

                        if ("failed".equalsIgnoreCase(status)) {
                            throw new RuntimeException("⚠ PDF receipt generation failed: " + statusResponse);
                        }
                        Thread.sleep(2000);
                    }

                    // 3) DOWNLOAD PDF AS BYTES
                    URL pdfUrl = new URL(fileUrl);
                    HttpURLConnection pdfConn = (HttpURLConnection) pdfUrl.openConnection();

                    try (InputStream is = pdfConn.getInputStream();
                         FileOutputStream fos = new FileOutputStream("NotaKantinSmart.pdf")) {

                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }

                    System.out.println("Receipt has been successfully created: NotaKantinSmart.pdf");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("⚠ - Please enter a number - ");
            scanner.next();
        }
    }

    public User getOwner(ArrayList<User>user) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).getRoles().equals(Main.Roles.OWNER.getRoleName())) {
                return user.get(i);
            }
        }
        return null;
    }

    public int getTotalWaktu() { // Jika belum mulai dimasak (waktuPesan masih 0), tampilkan totalWaktu utuh
        if (this.waktuPesan == 0) {
            return this.totalWaktu;
        }

        long waktuSekarang = System.currentTimeMillis() / 1000;
        long sisa = this.totalWaktu - (waktuSekarang - this.waktuPesan);

        // Cegah agar tidak mengembalikan angka minus jika delay antrean
        return sisa > 0 ? (int) sisa : 0;
    }

    public void addWaktu(int waktuPesan) {
        this.totalWaktu = waktuPesan * quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    } // encapsulation , method: getter

    public User getUser() {
        return user;
    } // encapsulation , method: getter

    public int getQuantity() {
        return quantity;
    } // encapsulation , method: getter

    @Override
    public String toString() {

        return foodItem.getFoodName() +  // encapsulation , method: getter
                " x" + quantity;
    }
}
