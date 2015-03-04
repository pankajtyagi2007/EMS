package com.aartek.eventManagement;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Application
{
	public void save()
	{

		// saving
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		// company data
		Company company = new Company();
		company.setCompanyName("aartek");

		// event data
		Event event = new Event();
		event.setEventName("political");

		Set<Event> eventSet = new HashSet<Event>();
		eventSet.add(event);

		company.setEvent(eventSet);
		event.setCompany(company);

		// speaker data
		Speaker speaker = new Speaker();
		speaker.setSpeakerName("ajay sir");

		speaker.setEvent(event);

		Set<Speaker> speakerSet = new HashSet<Speaker>();
		speakerSet.add(speaker);
		event.setSpeaker(speakerSet);

		// attendee data
		Attendee attendee1 = new Attendee("amar");
		Attendee attendee2 = new Attendee("akbar");

		Set<Attendee> attendees = new HashSet<Attendee>();
		attendees.add(attendee1);
		attendees.add(attendee2);

		event.setAttendee(attendees);

		// location data
		Location location = new Location();
		location.setLocationName("palasia");

		event.setLocation(location);
		location.setEvent(event);

		Transaction tx = session.beginTransaction();

		// session.save(company);
		session.save(event);
		// session.save(speaker);

		tx.commit();

		session.close();
		factory.close();
	}

	public void retrive()
	{
		// retrive
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		Query qry = session.createQuery("from Event event");
		List l = qry.list();
		Iterator it = l.iterator();

		while (it.hasNext())
		{

			Object o = it.next();
			Event event = (Event) o;
			System.out.println("event detail= " + event.getEventId() + " " + event.getEventName());

			System.out.println("location detail= " + event.getLocation().getLocationName() + " " + event.getLocation().getLocationId());

			System.out.println("company detail= " + event.getCompany().getCompanyId() + " " + event.getCompany().getCompanyName());

			Set<Attendee> s = event.getAttendee();
			Iterator<Attendee> it1 = s.iterator();
			System.out.println("+++++++++++++ it1 +++++++++" + it1);

			while (it1.hasNext())
			{
				Attendee attendee = it1.next();
				System.out.println("attendee detail= " + attendee.getAttendeeId() + " " + attendee.getAttendeeName());
			}

			Set<Speaker> sp = event.getSpeaker();
			Iterator<Speaker> it2 = sp.iterator();

			while (it2.hasNext())
			{
				Speaker speakers = it2.next();
				System.out.println("Speaker detail= " + speakers.getSpeakerId() + " " + speakers.getSpeakerName());
			}

		}

		session.close();
		factory.close();

	}

	public void delete()
	{

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		// Speaker speaker = (Speaker) session.get(Speaker.class, new Integer(1));
		// Event event = (Event) session.get(Event.class, new Integer(1));
		Company company = (Company) session.get(Company.class, new Integer(1));

		Transaction tx = session.beginTransaction();
		// session.delete(speaker);
		// session.delete(event);
		session.delete(company);

		tx.commit();

		session.close();

		factory.close();

	}

	public static void main(String[] args)
	{
		Application application = new Application();

		application.save();

		// application.retrive();

		// application.delete();
	}
}
