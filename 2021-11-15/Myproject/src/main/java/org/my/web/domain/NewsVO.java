package org.my.web.domain;

import org.json.JSONObject;

public class NewsVO {
	private String title;
	private String originallink;
	private String link;
	private String description;
	private String pubDate;
	
	public NewsVO(JSONObject items) {
		this.title = items.getString("title");
		this.originallink = items.getString("originallink");
		this.link = items.getString("link");
		this.description = items.getString("description");
		this.pubDate = items.getString("pubDate");
	}
	
	public String toString() {
		return "title : "+title+", "+"originallink : "+originallink+", "+"link : "+link+", "+"description : "+description+", "+"pubDate : "+pubDate;
	}
}
