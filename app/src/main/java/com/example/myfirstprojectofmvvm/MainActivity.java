package com.example.myfirstprojectofmvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    ScanOptions option1, option2;
    TextView resultData;
    Button barcodescanBtn, qrcodescanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultData = findViewById(R.id.resultdata);
        barcodescanBtn = findViewById(R.id.btnBarcodeScan);
        qrcodescanBtn = findViewById(R.id.btnQRCodeScan);
        option1 = new ScanOptions();
        option1.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES);
        option1.setPrompt("Scan a barcode");
        option1.setCameraId(0);  // Use a specific camera of the device

        option1.setOrientationLocked(false);
        option1.setBeepEnabled(false);
        option1.setBarcodeImageEnabled(true);


        barcodescanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barcodeLauncher.launch(option1);
            }
        });
        option2 = new ScanOptions();
        option2.setDesiredBarcodeFormats(ScanOptions.QR_CODE);

        qrcodescanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barcodeLauncher.launch(option2);
            }
        });

    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            resultData.setText(result.getContents());
        }
    });

}