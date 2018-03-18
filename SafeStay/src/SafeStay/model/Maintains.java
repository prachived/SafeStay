package SafeStay.model;

import java.sql.Timestamp;

public class Maintains {

	protected Users users;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Incidents getIncidents() {
		return incidents;
	}

	public void setIncidents(Incidents incidents) {
		this.incidents = incidents;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	protected Incidents incidents;
	protected Timestamp created;

	public Maintains(Users users, Incidents incidents, Timestamp created) {
		this.users = users;
		this.incidents = incidents;
		this.created = created;
	}

	public Maintains(Users users) {
		this.users = users;
	}

}
