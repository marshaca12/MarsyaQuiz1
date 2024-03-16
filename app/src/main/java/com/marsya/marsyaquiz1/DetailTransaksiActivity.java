package com.marsya.marsyaquiz1;

import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import java.text.NumberFormat;
        import java.util.Locale;

public class DetailTransaksiActivity extends AppCompatActivity {
    TextView tvJudulDetailTransaksi, tvInformasiTransaksi;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        tvJudulDetailTransaksi = findViewById(R.id.tvJudulDetailTransaksi);
        tvInformasiTransaksi = findViewById(R.id.tvInformasiTransaksi);
        btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        if (intent != null) {
            String namaPelanggan = intent.getStringExtra("nama_pelanggan");
            String tipePelanggan = intent.getStringExtra("tipe_pelanggan");
            String kodeBarang = intent.getStringExtra("kode_barang");
            int jumlahBarang = intent.getIntExtra("jumlah_barang", 0);
            double totalHarga = intent.getDoubleExtra("total_harga", 0);
            double diskonHarga = intent.getDoubleExtra("diskon_harga", 0);
            double diskonMember = intent.getDoubleExtra("diskon_member", 0);
            double totalBayar = intent.getDoubleExtra("total_bayar", 0);

            // Format total bayar ke dalam format mata uang RP
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            String formattedTotalHarga = formatter.format(totalHarga);
            String formattedDiskonHarga = formatter.format(diskonHarga);
            String formattedDiskonMember = formatter.format(diskonMember);
            String formattedTotalBayar = formatter.format(totalBayar);

            String informasiTransaksi = "TOKO HP Marsha Cahyani Dwisyakilla\nJalan Taman Karya\n\n"
                    + "Tipe Member: " + tipePelanggan +"\n"
                    + "Transaksi Anda Hari Ini\n"+ "\n"
                    + "Nama Pelanggan: " + namaPelanggan + "\n"
                    + "Jumlah Barang: " + jumlahBarang + "\n"
                    + "Nama Barang: " + getNamaBarang(kodeBarang) + "\n"
                    + "Harga Barang: " + formattedTotalHarga + "\n"
                    + "Total Harga: " + formattedTotalHarga + "\n"
                    + "Diskon Harga: " + formattedDiskonHarga + "\n"
                    + "Diskon Member: " + formattedDiskonMember + "\n"
                    + "Total Bayar: " + formattedTotalBayar + "\n"
                    + "Terima Kasih Atas Kepercayaan Anda!\n"
                    + "Simpan Bukti Pembayaran Ini Untuk bukti pembayaran yang sah !";

            tvJudulDetailTransaksi.setText("Detail Transaksi");
            tvInformasiTransaksi.setText(informasiTransaksi);
        }

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareStrukPembayaran();
            }
        });
    }

    private String getNamaBarang(String kodeBarang) {
        String namaBarang = "";

        switch (kodeBarang) {
            case "IPX":
                namaBarang = "Iphone X";
                break;
            case "MP3":
                namaBarang = "Macbook Pro M3";
                break;
            case "AV4":
                namaBarang = "Asus Vivobook 14";
                break;
        }

        return namaBarang;
    }

    private void shareStrukPembayaran() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        String informasiTransaksi = tvInformasiTransaksi.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, informasiTransaksi);

        startActivity(Intent.createChooser(intent, "Bagikan Struk Pembayaran Melalui"));
    }
}
