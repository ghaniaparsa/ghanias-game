public class TestYuhuu {
    public static void main(String[] args) {
        try {
            System.out.println(DatabaseManager.getConnection());
            System.out.println("KONEKSI BERHASIL");
        } catch (Exception e) {
            System.out.println("KONEKSI GAGAL");
            e.printStackTrace();
        }
    }
}