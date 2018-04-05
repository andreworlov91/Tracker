package ru.aorlov.models;

public class Item {
	private String id;

	public String name;

	public String description;

	public Item() {

	}

	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}