import java.net.URL;
import java.nio.charset.StandardCharsets;

// 1. URL Alamat Resmi DynamicPDF Cloud API (Tanpa sub-folder /file/create)
import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Tambahkan library JSON, misalnya Jackson atau org.json
// Contoh di bawah pakai org.json:
import org.json.JSONObject;


public class Purchase {

    private User user;
    private FoodItem foodItem;
    private int quantity;

    public long waktuPesan = System.currentTimeMillis() / 1000; // mencatat detik saat ini
    public int totalWaktu;

    public Purchase(User user, FoodItem foodItem, int quantity) {
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double getCalculateTotal() {
        return foodItem.getHarga() * quantity;
    }

    public double calculateCheck(User user, FoodItem foodItem, int quantity) {
        return user.saldo - foodItem.harga * quantity;

    }

    public void printReceipt(ArrayList<User>user) {
        User owner = getOwner(user);
        owner.tambahSaldo(getCalculateTotal());

        System.out.println("Transaction completed! you bought " + foodItem.foodName + " for " + this.quantity);
        System.out.println("Cost: " + getCalculateTotal());
        try {
            // 1) CREATE JOB
            URL url = new URL("https://apdf.io/api/pdf/file/create");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer 18CxweWKjZ9ZPcsBoEG5Aob16h5WtZsh1b1icIua9e358837");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            String notaHtml = "<html><body>"
                    + "<h2>NOTE SMART CANTEEN</h2>"
                    + "<p>Product: " + getFoodItem().getFoodName() + "</p>"
                    + "<p>Quantity: " + getQuantity() + "</p>"
                    + "<p>Total: " + getCalculateTotal() + "</p>"
                    + "<br>" + "<br>"
                    + "<P>Thanks for shooping by! :)</p>"
                    + "</body></html>";

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
                    throw new RuntimeException("PDF generation failed: " + statusResponse);
                }
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

            System.out.println("PDF berhasil dibuat: NotaKantinSmart.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getOwner(ArrayList<User>user) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).getRoles().equals(Main.Roles.OWNER)) {
                return user.get(i);
            }
        }
        return null;
    }

    public void addWaktu(int waktuPesan) {
        this.totalWaktu = waktuPesan * quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public User getUser() {
        return user;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {

        return foodItem.getFoodName() +
                " x" + quantity;
    }
}
