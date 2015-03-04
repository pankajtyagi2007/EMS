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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ATTENDEE_TABLE", catalog = "EVENT_MANAGEMENT")
public class Attendee implements Serializable
{
	private Integer			attendeeId;
	private String			attendeeName;
	private Set<Event>	event;

	public Attendee()
	{

	}

	public Attendee(String attendeeName)
	{
		this.attendeeName = attendeeName;
	}

	public Attendee(String attendeeName, Set<Event> event)
	{
		this.attendeeName = attendeeName;
		this.event = event;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTENDEE_ID", nullable = false, unique = true)
	public Integer getAttendeeId()
	{
		return attendeeId;
	}

	public void setAttendeeId(Integer attendeeId)
	{
		this.attendeeId = attendeeId;
	}

	@Column(name = "ATTENDEE_NAME", nullable = false, unique = false)
	public String getAttendeeName()
	{
		return attendeeName;
	}

	public void setAttendeeName(String attendeeName)
	{
		this.attendeeName = attendeeName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "attendee", cascade = CascadeType.ALL)
	public Set<Event> getEvent()
	{
		return event;
	}

	public void setEvent(Set<Event> event)
	{
		this.event = event;
	}

}
