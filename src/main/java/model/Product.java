package model;

import java.util.Map;

// this model was sourced from Eric Liao of CSULA
// https://github.com/csula/cs454-winter-2016/blob/master/src/main/java/edu/csula/cs454/es/models/Product.java


public class Product {
	private final long id;
	private final String description;
	private final Map<String, String> attributes;

	public Product(long id, String description, Map<String, String> attributes) {
		this.id = id;
		this.description = description;
		this.attributes = attributes;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
}
