package com.example.mlr.ncut_class_form_3a432001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView output; //購票結果
    private RadioGroup radioGroup;
    private RadioButton rdb01, rdb02;
    private CheckBox ckb01, ckb02, ckb03;
    private EditText edt01, edt02, edt03;
    String msg = ""; //電影票資訊
    String adult=""; //成人票
    String student=""; //學生票
    String sale=""; //促銷票

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output =(TextView) findViewById(R.id.txv_show);

        //找出RadioGroup和RadioButton元件，指定給radioGroup,rdb01,rdb02
        radioGroup = (RadioGroup) findViewById(R.id.rdob_movie);
        rdb01 = (RadioButton) findViewById(R.id.rdb_1);
        rdb02 = (RadioButton) findViewById(R.id.rdb_2);
        //註冊radioGroup傾聽者物件
        radioGroup.setOnCheckedChangeListener(listener);

        //找到CheckBox元件指定給ckb01, ckb02, ckb03
        ckb01 = (CheckBox) findViewById(R.id.ckb_1);
        ckb02 = (CheckBox) findViewById(R.id.ckb_2);
        ckb03 = (CheckBox) findViewById(R.id.ckb_3);
        //註冊CheckBox的傾聽者物件
        ckb01.setOnCheckedChangeListener(ckblistener);
        ckb02.setOnCheckedChangeListener(ckblistener);
        ckb03.setOnCheckedChangeListener(ckblistener);

        //找到EditText傾聽者物件指定給edt01, edt02, edt03
        edt01 = (EditText) findViewById(R.id.edt_num1) ;
        edt02 = (EditText) findViewById(R.id.edt_num2) ;
        edt03 = (EditText) findViewById(R.id.edt_num3) ;
        //註冊EditText傾聽者物件
        edt01.addTextChangedListener(listener1);
        edt02.addTextChangedListener(listener1);
        edt03.addTextChangedListener(listener1);
    }

    //建立RadioGroup傾聽者物件
    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){
        public void onCheckedChanged(RadioGroup group, int checkedId){
            //如果rdb01被選取，則顯示鋼鐵人大戰超人的購票資訊
            if (checkedId == rdb01.getId()){
                msg = "你的購票資訊如下："+ "\n" +rdb01.getText() +"\n";
                //如果rdb02被選取，則顯示隊長單挑蝙蝠俠的購票資訊
            }else if(checkedId == rdb02.getId()){
                msg = "你的購票資訊如下："+ "\n" + rdb02.getText() +"\n";
                //如果都沒被選取，則顯示請購票!
            }else{
                msg = "請購票!";

            }
            output.setText(msg);//顯示購票資訊的文字


        }
    };

    //建立EditText的傾聽者物件
    private TextWatcher listener1 = new TextWatcher(){
        //當文字被改變之前
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        //當文字正在被改變
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (ckb01.isChecked()){  //如果ckb01被選取
                if(edt01.length()>0){  //如果edt01的文字長度大於0，表示有數字，則顯示成人票有幾張，共多少錢
                    int quantity = Integer.parseInt(edt01.getText().toString()); //將數字轉換成文字
                    adult = "成人票"+ edt01.getText() + "張共" + quantity * 280 +"\n"; // 成人票=數量*280
                }else {
                    adult = "" ; //如果edt01的文字長度不大於0，則不顯示成人票的所有資訊
                }
            } else {
                adult = ""; //如果ckb01沒被選取，則不顯示成人票的所有資訊
            }

            if (ckb02.isChecked()){ //如果ckb02被選取
                if(edt02.length()>0){ //如果edt02的文字長度大於0，表示有數字，則顯示學生票有幾張，共多少錢
                    int quantity = Integer.parseInt(edt02.getText().toString()); //將數字轉換成文字
                    student = "學生票"+ edt02.getText() + "張共" + quantity * 230 +"\n"; //學生票=數量*230
                }else {
                    student = ""; //如果edt02的文字長度不大於0，則不顯示學生票的所有資訊
                }
            } else {
                student = ""; //如果ckb02沒被選取，則不顯示學生票的所有資訊
            }

            if (ckb03.isChecked()){ //如果ckb03被選取

                if(edt03.length()>0){ //如果edt03的文字長度大於0，表示有數字，則顯示促銷票有幾張，共多少錢
                    int quantity = Integer.parseInt(edt03.getText().toString()); //將數字轉換成文字
                    sale = "促銷票"+ edt03.getText() + "張共" + quantity * 180 +"\n"; //促銷票=數量*180
                }else {
                    sale = ""; //如果edt03的文字長度不大於0，則不顯示促銷票的所有資訊
                }
            } else {
                sale = ""; //如果ckb03沒被選取，則不顯示促銷票的所有資訊
            }
            output.setText(msg + adult + student + sale); //顯示購票資訊

        }
        //當文字被改變以後
        public void afterTextChanged(Editable s) {

        }

    };

    //建立CheckBox的傾聽者物件
    public CheckBox.OnCheckedChangeListener ckblistener = new CheckBox.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.ckb_1:
                    if (isChecked) { //如果ckb01被選取
                        if (edt01.length()>0){ //如果edt01的文字長度大於0，表示有數字，則顯示成人票有幾張，共多少錢
                            int quantity = Integer.parseInt(edt01.getText().toString()); //將數字轉換成文字
                            adult = "成人票" + edt01.getText() + "張共" + quantity * 280 +"\n"; //成人票=數量*280
                        }else{
                            adult = "" ; //如果edt01的文字長度不大於0，則不顯示成人票的所有資訊
                        }
                    } else  {
                        adult=""; //如果ckb01沒被選取，則不顯示成人票的所有資訊
                    }
                    break;
                case R.id.ckb_2:
                    if (isChecked) { //如果ckb02被選取
                        if (edt02.length()>0){//如果edt02的文字長度大於0，表示有數字，則顯示學生票有幾張，共多少錢
                            int quantity = Integer.parseInt(edt02.getText().toString()); //將數字轉換成文字
                            student = "學生票" + edt02.getText() + "張共" + quantity * 230 +"\n"; //學生票=數量*230
                        }else{
                            student = "" ; //如果edt02的文字長度不大於0，則不顯示學生票的所有資訊
                        }
                    } else  {
                        student=""; //如果ckb02沒被選取，則不顯示學生票的所有資訊
                    }
                    break;
                case R.id.ckb_3:
                    if (isChecked) { //如果ckb03被選取

                        if (edt03.length()>0){ //如果edt03的文字長度大於0，表示有數字，則顯示促銷票有幾張，共多少錢
                            int quantity = Integer.parseInt(edt03.getText().toString()); //將數字轉換成文字
                            sale = "促銷票" + edt03.getText() + "張共" + quantity * 180 +"\n"; //促銷票=數量*180

                        }else{
                            sale = "" ; //如果edt03的文字長度不大於0，則不顯示促銷票的所有資訊
                        }
                    } else  {
                        sale=""; //如果ckb03沒被選取，則不顯示促銷票的所有資訊
                    }
                    break;
            }
            output.setText(msg + adult + student + sale); //顯示購票資訊
        }

    };




}
