package ba.bitcamp.nerminVucinic.homework.xml;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * class that represents news article from rss feed(klix.ba)
 * @author nermin
 *
 */
public class Article {
	public String title;
	public String clanak;

	public Article(String title, String clanak) {
		this.title = title;
		this.clanak = clanak;
	}

	@Override
	public String toString() {
		return title + "\n\n" + clanak;
	}

}