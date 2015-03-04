package com.aartek.eventManagement;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EVENT_TABLE", catalog = "EVENT_MANAGEMENT", uniqueConstraints = {
				@UniqueConstraint(columnNames = "EVENT_NAME") })
public class Event implements Serializable
{
	private Integer				eventId;
	private String				eventName;
	private Location			location;
	private Set<Attendee>	attendee;
	private Company				company;
	private Set<Speaker>	Speaker;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EVENT_ID", unique = true, nullable = false)
	public Integer getEventId()
	{
		return eventId;
	}

	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}

	@Column(name = "EVENT_NAME", unique = true, nullable = false, length = 20)
	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "EVENT_ATTENDEE", catalog = "EVENT_MANAGEMENT", joinColumns = {
					@JoinColumn(name = "EVENT_ID", nullable = false, updatable = false) },
					inverseJoinColumns = { @JoinColumn(name = "ATTENDEE_ID", nullable = false, updatable = false) })
	public Set<Attendee> getAttendee()
	{
		return attendee;
	}

	public void setAttendee(Set<Attendee> attendee)
	{
		this.attendee = attendee;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
	public Set<Speaker> getSpeaker()
	{
		return Speaker;
	}

	public void setSpeaker(Set<Speaker> speaker)
	{
		Speaker = speaker;
	}

}
