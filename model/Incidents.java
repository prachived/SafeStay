package safety.model;

import java.util.Date;

public class Incidents {
	protected String incidentId;
	protected Offenses offense;
	protected String district;
	protected int reportingArea;
	protected Shootings shooting;
	protected Date occuredOnDate;
	protected String dayOfWeek;
	protected int hour;
	protected UCRs ucr;
	protected Addresses address;
	
	public enum Shootings{
		Y,
	}
	
	public enum UCRs{
		PartOne, PartTwo, PartThree
	}
	
	public Incidents(String incidentId, Offenses offense, String district,
			int reportingArea, Shootings shooting, Date occuredOnDate,
			String dayOfWeek, int hour, UCRs ucr, Addresses address) {
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
	
	public Incidents(String incidentId) {
		this.incidentId = incidentId;
	}
	
	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	
	public Offenses getOffense() {
		return offense;
	}

	public void setOffense(Offenses offense) {
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

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}
}
