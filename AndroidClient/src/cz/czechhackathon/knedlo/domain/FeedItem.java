package cz.czechhackathon.knedlo.domain;

import java.io.Serializable;

import android.view.View;

/**
 * POJO with one feed item info
 * @author shmoula
 *
 */
public class FeedItem implements Serializable {
	private static final long serialVersionUID = -476311587722546742L;

	transient private View boundView;
	
	private String title;
	private String perex;
	private String text;
	private String imageUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPerex() {
		return perex;
	}
	public void setPerex(String perex) {
		this.perex = perex;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setBoundView(View view) {
		this.boundView = view;
	}
	public View getBoundView() {
		return boundView;
	}
}
