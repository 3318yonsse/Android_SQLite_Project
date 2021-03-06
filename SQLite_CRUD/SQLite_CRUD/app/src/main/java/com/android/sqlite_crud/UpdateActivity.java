package com.android.sqlite_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {


    Button modi_btn;
    EditText modi_sno, modi_userid, modi_userpw, modi_username, modi_usertel, modi_usermajor;
    StudentInfo studentInfo;
    int onStartCount = 0;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        studentInfo = new StudentInfo(UpdateActivity.this);

        findViewById(R.id.modi_btn).setOnClickListener(mClickListener);
        findViewById(R.id.cancel_btn).setOnClickListener(rClickListener);

        Intent intent = getIntent(); //보낸값 여기서 받는다.

        String sno = intent.getStringExtra("sno");
        String userid = intent.getStringExtra("userid");
        String userpw = intent.getStringExtra("userpw");
        String username = intent.getStringExtra("username");
        String usertel = intent.getStringExtra("usertel");
        String usermajor = intent.getStringExtra("usermajor");

        modi_sno.setText(sno);
        modi_sno.setText(userid);
        modi_sno.setText(userpw);
        modi_sno.setText(username);
        modi_sno.setText(usertel);
        modi_sno.setText(usermajor);
    }



    View.OnClickListener mClickListener = new View.OnClickListener() {
        SQLiteDatabase DB; // 이걸 선언해줘야 SQL 명령어를 쓸수 있다.
        @Override
        public void onClick(View v) {
            modi_sno = findViewById(R.id.modi_sno);
            modi_userid = findViewById(R.id.modi_userid);
            modi_userpw = findViewById(R.id.modi_userpw);
            modi_username = findViewById(R.id.modi_username);
            modi_usertel = findViewById(R.id.modi_usertel);
            modi_usermajor = findViewById(R.id.modi_usermajor);


            if(modi_userid.getText().toString().length() == 0 || modi_userpw.getText().toString().length() == 0
                    || modi_username.getText().toString().length() == 0 || modi_usertel.getText().toString().length() == 0
                    || modi_usermajor.getText().toString().length() == 0){

                    Toast.makeText(UpdateActivity.this,"빈칸을 모두 입력하세요", Toast.LENGTH_SHORT).show();
            }else{
                try {
//                        getReadableDatabase()
//                        이력 수정 삭제에서
                    DB = studentInfo.getWritableDatabase(); // 입력 수정 삭제는 똑같다. 포멧+
                    String query = "UPDATE student SET username = '"+ modi_username.getText().toString() + "' WHERE id = '" + modi_sno.getText().toString() + "' ;";
                    DB.execSQL(query);
                    studentInfo.close();
                    Toast.makeText(UpdateActivity.this,"Completely Modified", Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(UpdateActivity.this,"UPDATE Error", Toast.LENGTH_SHORT).show();
                }

            }
            intent = new Intent(UpdateActivity.this,ListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
    };

    View.OnClickListener rClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(UpdateActivity.this,ListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    };




}///--------END
