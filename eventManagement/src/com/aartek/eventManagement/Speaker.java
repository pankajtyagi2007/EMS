package com.aartek.eventManagement;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPEAKER_TABLE", catalog = "EVENT_MANAGEMENT")
public class Speaker implements Serializable
{

	private Integer	speakerId;
	private String	speakerName;
	private Event		event;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPEAKER_ID", unique = true, nullable = false)
	public Integer getSpeakerId()
	{
		return speakerId;
	}

	public void setSpeakerId(Integer speakerId)
	{
		this.speakerId = speakerId;
	}

	@Column(name = "SPEAKER_NAME", unique = false, nullable = false)
	public String getSpeakerName()
	{
		return speakerName;
	}

	public void setSpeakerName(String speakerName)
	{
		this.speakerName = speakerName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_ID", nullable = false)
	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

}
