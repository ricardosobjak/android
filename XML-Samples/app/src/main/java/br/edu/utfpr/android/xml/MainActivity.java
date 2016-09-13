package br.edu.utfpr.android.xml;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.android.xml.model.Message;
import br.edu.utfpr.android.xml.parsers.FeedParser;
import br.edu.utfpr.android.xml.parsers.FeedParserFactory;

public class MainActivity extends AppCompatActivity {

    private List<Message> messages;

    private ListView listView;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(android.R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent viewMessage = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(messages.get(position).getLink().toExternalForm()));
                startActivity(viewMessage);
            }
        });

        new Thread(new LoadFeed(ParserType.ANDROID_SAX)).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, ParserType.ANDROID_SAX.ordinal(),
                ParserType.ANDROID_SAX.ordinal(), R.string.android_sax);
        menu.add(Menu.NONE, ParserType.SAX.ordinal(), ParserType.SAX.ordinal(),
                R.string.sax);
        menu.add(Menu.NONE, ParserType.DOM.ordinal(), ParserType.DOM.ordinal(),
                R.string.dom);
        menu.add(Menu.NONE, ParserType.XML_PULL.ordinal(),
                ParserType.XML_PULL.ordinal(), R.string.pull);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        ParserType type = ParserType.values()[item.getItemId()];
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        if (adapter.getCount() > 0){
            adapter.clear();
        }
        //this.loadFeed(type);
        new Thread(new LoadFeed(type)).start();
        return true;
    }



    private String writeXml(){
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "messages");
            serializer.attribute("", "number", String.valueOf(messages.size()));
            for (Message msg: messages){
                serializer.startTag("", "message");
                serializer.attribute("", "date", msg.getDate());
                serializer.startTag("", "title");
                serializer.text(msg.getTitle());
                serializer.endTag("", "title");
                serializer.startTag("", "url");
                serializer.text(msg.getLink().toExternalForm());
                serializer.endTag("", "url");
                serializer.startTag("", "body");
                serializer.text(msg.getDescription());
                serializer.endTag("", "body");
                serializer.endTag("", "message");
            }
            serializer.endTag("", "messages");
            serializer.endDocument();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    class LoadFeed implements Runnable {
        private ParserType type;

        public LoadFeed(ParserType type) {
            this.type = type;
        }

        @Override
        public void run() {
            loadFeed();
        }

        private void loadFeed(){
            try{
                Log.i("AndroidNews", "ParserType="+type.name());
                FeedParser parser = FeedParserFactory.getParser(type);
                long start = System.currentTimeMillis();
                messages = parser.parse();
                long duration = System.currentTimeMillis() - start;
                Log.i("AndroidNews", "Parser duration=" + duration);
                String xml = writeXml();
                Log.i("AndroidNews", xml);
                final List<String> titles = new ArrayList<String>(messages.size());
                for (Message msg : messages){
                    titles.add(msg.getTitle());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(MainActivity.this, R.layout.row, titles);
                        listView.setAdapter(adapter);
                    }
                });

            } catch (Throwable t){
                Log.e("AndroidNews",t.getMessage(),t);
            }
        }
    }

}
