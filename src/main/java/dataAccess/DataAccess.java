package dataAccess;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import hibernate.HibernateUtil;


/**
 * It implements the data access to the objectDb database
 */
public class DataAccess implements DataAccessInterface {
	
	protected static EntityManagerFactory emf;



	public DataAccess()  {	
		
	}
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		Session db = HibernateUtil.getSessionFactory().getCurrentSession();
		db.beginTransaction();
	
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event( "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event( "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event( "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event( "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event( "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event( "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event( "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event( "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event( "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
			Session db = HibernateUtil.getSessionFactory().getCurrentSession();
			db.beginTransaction();
			
			System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
	
			System.out.println(db+" "+event);
		
		
			Event evento = event;
			
			if (evento.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
		
			Question q = evento.addQuestion(question, betMinimum);
			db.persist(q);
			// db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.update(evento);
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents(Date date) {
		Session db = HibernateUtil.getSessionFactory().getCurrentSession();
		db.beginTransaction();
		
		org.hibernate.Query q = db.createQuery("from Event where eventDate= :data");
		q.setParameter("data", date);
		List<Event> result = q.list();
		db.getTransaction().commit();
		return result;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		Session db = HibernateUtil.getSessionFactory().getCurrentSession();
		db.beginTransaction();
		System.out.println(">> DataAccess: getEventsMonth");
		
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		org.hibernate.Query query = db.createQuery("from Event where eventDate between ?1 and ?2");   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.list();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
			db.getTransaction().commit();

	 	return res;
	}


	
}