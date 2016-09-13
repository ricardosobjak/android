package br.edu.utfpr.android.xml;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.android.xml.model.Message;
import br.edu.utfpr.android.xml.parsers.FeedParser;
import br.edu.utfpr.android.xml.parsers.FeedParserFactory;

public class MessageList extends ListActivity {

}