package com.example.miniproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

Button btnplay;
    TextView welcomeMassage;
ListView lvmenu;
ListMenuAdapter adapter;
    ArrayList<MenuActivity> arrayListMenu;

    private final String REQUIRE = "Require";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Intent intent = getIntent();
//        String username = intent.getStringExtra("USERNAME");
//        welcomeMassage = findViewById(R.id.welcomeMassage);
////        String username = getIntent().getStringExtra("username");
//        welcomeMassage.setText("Welcome, " + username + "!");

        btnplay= findViewById(R.id.playgame);
        btnplay.setOnClickListener(this);
        AnhXa();
        adapter= new ListMenuAdapter(this, R.layout.activity_listmenu, arrayListMenu);
        lvmenu.setAdapter(adapter);

    }
    private void AnhXa(){
        lvmenu= (ListView) findViewById(R.id.listView);
        arrayListMenu= new ArrayList<>();
        arrayListMenu.add(new MenuActivity(" 🏁 Giải đua đặc biệt cá cược vào ngày 23/05/2024", "Vào slot 1 của Tiết PRM392 sẽ có 1 ván chơi cá cược đặt biệt diễn ra. Với thể lệ đặt cược tiền vào chiếc xe may mắn và bạn sẽ thu được tiền thưởng hoặc không! ",R.drawable.logo_duaxe));
        arrayListMenu.add(new MenuActivity(" 🚩 QUY ĐỊNH MỚI NHẤT CỦA CUỘC CHƠI ", "Nếu bạn chơi về 1$ hãy dừng lại. Nếu như vẫn tiếp tục chơi và chơi xuống dưới 0 sẽ ghi nợ!!!!. Và Game sẽ dừng lại và kết thúc!!!",R.drawable.img3));
        arrayListMenu.add(new MenuActivity(" ❤️‍🔥 Những tính năng mới được update", "Số lượng xe 3 chiếc: Đỏ, Xanh, Lục với tốc độ chạy may rủi, tính bằng ms. Xác suất chiến thắng của mỗi xe sẽ là ngẫu nhiên. Không thể đoán trước điều gì sẽ xảy ra. ",R.drawable.img3));
        arrayListMenu.add(new MenuActivity(" 💵 Tính năng lựa chọn đặt cược số tiền", "Với tham vọng càng lớn, đặt cược số tiền là sự lựa chọn hợp lí để cho những người chơi có thể chơi Máu 🔥 nhất ",R.drawable.img1));


    }


    private void gameActivity(){
        Intent intent= new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playgame) {
          gameActivity();
        }

    }
}
