package com.fc.mydemo.activity.xml;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.fc.mydemo.R;
import com.fc.mydemo.xmlparse.SAXPraserHelper;
import com.fc.mydemo.xmlparse.channel;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserDemo extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = null;
        try {
            adapter = new SimpleAdapter(this, getData(), R.layout.activity_list, new String[]{"name", "id"}, new int[]{
                    R.id.textName, R.id.textId
            });
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setListAdapter(adapter);
    }

    private List<Map<String, String>> getData() throws ParserConfigurationException, SAXException, IOException {
        List<channel> list;
        list = getChannelList();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", list.get(i).getName());
            map.put("id", list.get(i).getId());
            mapList.add(map);
        }
        return mapList;

    }

    private List<channel> getChannelList() throws ParserConfigurationException, SAXException, IOException {
        //实例化一个SAXParserFactory对象
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        //实例化SAXParser对象，创建XMLReader对象，解析器
        parser = factory.newSAXParser();
        XMLReader xmlReader = parser.getXMLReader();
        //实例化handler，事件处理器
        SAXPraserHelper helperHandler = new SAXPraserHelper();
        //解析器注册事件
        xmlReader.setContentHandler(helperHandler);
        //读取文件流
        InputStream stream = getResources().openRawResource(R.raw.channels);
        InputSource is = new InputSource(stream);
        //解析文件
        xmlReader.parse(is);
        return helperHandler.getList();
    }


}
