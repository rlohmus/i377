package database.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@NamedQueries({
	@NamedQuery(name="Unit.findAll", query="SELECT u FROM Unit u"),
	@NamedQuery(name="Unit.search", query="SELECT u FROM Unit u WHERE lower(u.name) LIKE :keyword"),
	@NamedQuery(name="Unit.findChildUnits", query="SELECT u FROM Unit u WHERE u.unit.id = :id"),
	@NamedQuery(name="Unit.findUnit", query="SELECT u FROM Unit u WHERE u.id = :id"),
	@NamedQuery(name="Unit.findSuperUnit", query="SELECT u.unit FROM Unit u WHERE u.id = :id"),
	@NamedQuery(name="Unit.UnitByCode", query="SELECT u FROM Unit u WHERE u.code = :code")
	
})

/**
 * The persistent class for the UNIT database table.
 * 
 */
@Entity
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNIT_ID_GENERATOR", sequenceName="PUBLIC.SEQ1")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIT_ID_GENERATOR")
	private long id;

	private String code;

	private String name;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="SUPER_UNIT_ID")
	private Unit unit;

	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="unit")
	private List<Unit> units;

	public Unit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Unit> getUnits() {
		return this.units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public Unit addUnit(Unit unit) {
		getUnits().add(unit);
		unit.setUnit(this);

		return unit;
	}

	public Unit removeUnit(Unit unit) {
		getUnits().remove(unit);
		unit.setUnit(null);

		return unit;
	}

}