package br.edu.utfpr.android.xml.parsers;

import java.util.List;

import br.edu.utfpr.android.xml.model.Message;

public interface FeedParser {
	List<Message> parse();
}
