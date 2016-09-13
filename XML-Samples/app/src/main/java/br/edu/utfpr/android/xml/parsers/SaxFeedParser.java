package br.edu.utfpr.android.xml.parsers;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.edu.utfpr.android.xml.RssHandler;
import br.edu.utfpr.android.xml.model.Message;


public class SaxFeedParser extends BaseFeedParser {

	public SaxFeedParser(String feedUrl){
		super(feedUrl);
	}
	
	public List<Message> parse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			RssHandler handler = new RssHandler();
			parser.parse(this.getInputStream(), handler);
			return handler.getMessages();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}