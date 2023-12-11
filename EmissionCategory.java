package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "emissionCategory")
public class EmissionCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String categoryName;

	private String categoryDescription;

	@OneToOne
	private Emission emission;

	public EmissionCategory() {
	}

	public EmissionCategory(int id, String categoryName, String categoryDescription, Emission emission) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.emission = emission;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@XmlElement
	public Emission getEmission() {
		return emission;
	}

	public void setEmission(Emission emission) {

		this.emission = emission;
	}

	@XmlElement
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;

	}

	@Override
	public String toString() {
		return "EmissionCategory [id=" + id + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + ", emission=" + emission + "]";
	}

}
