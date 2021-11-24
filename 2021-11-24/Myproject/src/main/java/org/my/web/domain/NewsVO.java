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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginallink() {
		return originallink;
	}

	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String toString() {
		return "title : "+title+", "+"originallink : "+originallink+", "+"link : "+link+", "+"description : "+description+", "+"pubDate : "+pubDate;
	}
}
