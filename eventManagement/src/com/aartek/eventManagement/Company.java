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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "company_table", catalog = "EVENT_MANAGEMENT", uniqueConstraints = {
				@UniqueConstraint(columnNames = "COMPANY_NAME") })
public class Company implements Serializable
{

	private Integer			companyId;
	private String			companyName;
	private Set<Event>	event;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_ID", nullable = false, unique = true)
	public Integer getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(Integer companyId)
	{
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_NAME", nullable = false, unique = true)
	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
	public Set<Event> getEvent()
	{
		return event;
	}

	public void setEvent(Set<Event> event)
	{
		this.event = event;
	}

}
