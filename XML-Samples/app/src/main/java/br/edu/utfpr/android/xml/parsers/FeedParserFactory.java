package br.edu.utfpr.android.xml.parsers;

import br.edu.utfpr.android.xml.ParserType;

public abstract class FeedParserFactory {
	static String feedUrl = "http://g1.globo.com/dynamo/brasil/rss2.xml";
	
	public static FeedParser getParser(){
		return getParser(ParserType.ANDROID_SAX);
	}
	
	public static FeedParser getParser(ParserType type){
		switch (type){
			case SAX:
				return new SaxFeedParser(feedUrl);
			case DOM:
				return new DomFeedParser(feedUrl);
			case ANDROID_SAX:
				return new AndroidSaxFeedParser(feedUrl);
			case XML_PULL:
				return new XmlPullFeedParser(feedUrl);
			default: return null;
		}
	}
}