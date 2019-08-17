package com.example.paysm.r1614263_2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import android.widget.RadioGroup;


@SuppressLint("NewApi")
public class WeatherActivity extends Activity {
    TextView textview;
    EditText editText;
    Document doc = null;
    LinearLayout layout = null;
    RadioGroup rg;
    String name;
    int num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        textview = (TextView) findViewById(R.id.textView1);
        editText = findViewById(R.id.editText);

        rg = (RadioGroup)findViewById(R.id.radioGroup);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        num = intent.getIntExtra("number",1);
        textview.setText(name+" 일기예보");

        if(num==1) {
            GetXMLTask task = new GetXMLTask(this);
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1117053000");
        }
        else if(num==2){
            GetXMLTask task = new GetXMLTask(this);
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162069500");
        }
        else if(num==3){
            GetXMLTask task = new GetXMLTask(this);
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1135059500");
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // private inner class extending AsyncTask
    @SuppressLint("NewApi")
    private class GetXMLTask extends AsyncTask<String, Void, Document> {
        private Activity context;

        public GetXMLTask(Activity context) {
            this.context = context;
        }

        @Override
        protected Document doInBackground(String... urls) {

            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db= dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Log.d("doInBackground()", "e : " + e);

            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String s = "";
            NodeList nodeList = doc.getElementsByTagName("data");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;

                NodeList timeList = fstElmnt.getElementsByTagName("hour");
                Element timeElement = (Element) timeList.item(0);
                timeList = timeElement.getChildNodes();

                s +=  ((Node) timeList.item(0)).getNodeValue() + "시 ";

                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                Element websiteElement = (Element) websiteList.item(0);
                websiteList = websiteElement.getChildNodes();
                s += ((Node) websiteList.item(0)).getNodeValue()+ " ";

                NodeList nameList = fstElmnt.getElementsByTagName("temp");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();

                s +=  ((Node) nameList.item(0)).getNodeValue() + "도 "+ "\n";
            }
            editText.setText(s);
        }

    }
}