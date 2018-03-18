package SafeStay.model;

import java.util.Date;

public class Incidents {
	protected int incidentId;
	protected Offense offense;
	protected String district;
	protected int reportingArea;
	protected Shootings shooting;
	protected Date occuredOnDate;
	protected String dayOfWeek;
	protected int hour;
	protected UCRs ucr;
	protected Address address;
	
	public enum Shootings{
		Y,
	}
	
	public enum UCRs{
		PartOne, PartTwo, PartThree
	}
	
	public Incidents(int incidentId, Offense offense, String district,
			int reportingArea, Shootings shooting, Date occuredOnDate,
			String dayOfWeek, int hour, UCRs ucr, Address address) {
		this.incidentId = incidentId;
		this.offense = offense;
		this.district = district;
		this.reportingArea = reportingArea;
		this.shooting = shooting;
		this.occuredOnDate = occuredOnDate;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.ucr = ucr;
		this.address = address;
	}
	
	public Incidents(int incidentId) {
		this.incidentId = incidentId;
	}
	
	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	
	public Offense getOffense() {
		return offense;
	}

	public void setOffense(Offense offense) {
		this.offense = offense;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getReportingArea() {
		return reportingArea;
	}

	public void setReportingArea(int reportingArea) {
		this.reportingArea = reportingArea;
	}

	public Shootings getShooting() {
		return shooting;
	}

	public void setShooting(Shootings shooting) {
		this.shooting = shooting;
	}

	public Date getOccuredOnDate() {
		return occuredOnDate;
	}

	public void setOccuredOnDate(Date occuredOnDate) {
		this.occuredOnDate = occuredOnDate;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public UCRs getUcr() {
		return ucr;
	}

	public void setUcr(UCRs ucr) {
		this.ucr = ucr;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
