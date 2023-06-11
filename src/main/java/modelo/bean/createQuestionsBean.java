package modelo.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

@ManagedBean
public class createQuestionsBean {
	
	private Date fecha; 
	private Event evento;
	private List<Event> eventos = new ArrayList<Event>();

	private String question;
	private double minBet;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		this.evento = evento;
	}
	public List<Event> getEventos() {
		return eventos;
	}
	public void setEventos(List<Event> eventos) {
		this.eventos = eventos;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public double getMinBet() {
		return minBet;
	}
	public void setMinBet(double minBet) {
		this.minBet = minBet;
	}
	
	private BLFacade BL = FacadeBean.getBusinessLogic();
		
	public void onDateSelect(SelectEvent fecha) {
		 
		eventos = BL.getEvents(this.fecha);
		}
	
	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
			System.out.println(evento);
			BL.createQuestion(evento, question, (float) minBet);
			System.out.println("Pregunta creada correctamente!");
		}
}
