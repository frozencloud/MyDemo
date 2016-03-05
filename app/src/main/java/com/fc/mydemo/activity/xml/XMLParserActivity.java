package com.fc.mydemo.activity.xml;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_xml_parser)
public class XMLParserActivity extends Activity {

    @ViewById(R.id.btnSAX)
    protected Button btnSax;
    @ViewById(R.id.btnPull)
    protected Button btnPull;
    @ViewById(R.id.btnDom)
    protected Button btnDom;

    @Click({R.id.btnSAX, R.id.btnPull, R.id.btnDom})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.btnSAX:
                Intent intent = new Intent();
                intent.setClass(XMLParserActivity.this, SAXParserDemo.class);
                startActivity(intent);
                break;
            case R.id.btnPull:
                intent = new Intent();
                intent.setClass(XMLParserActivity.this, PullParserDemo.class);
                startActivity(intent);
                break;
            case R.id.btnDom:
                intent = new Intent();
                intent.setClass(XMLParserActivity.this, DomParserDemo.class);
                startActivity(intent);
                break;
        }
    }
}