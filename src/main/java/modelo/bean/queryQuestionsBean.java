package modelo.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class queryQuestionsBean {

private Date fecha; 
private Event evento;
private List<Event> eventos = new ArrayList<Event>();
private String question;
private List<Question> questions = new ArrayList<Question>();

private double minBet;

	public queryQuestionsBean() {
		
	}
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
	public List<Question> getQuestions() {
			return questions;
		}
		public void setQuestions(List<Question> questions) {
			this.questions = questions;
		}
	private BLFacade BL = FacadeBean.getBusinessLogic();
	
	public void onDateSelect(SelectEvent fecha) {
		 
		eventos = BL.getEvents(this.fecha);
		}
	
	public void onSelect(SelectEvent e) {
		for (int i = 0; i<eventos.size(); i++) {
			if (BL.getEvents(fecha).get(i).getEventNumber().equals(evento.getEventNumber())) {
				questions = BL.getEvents(fecha).get(i).getQuestions();
				break;
			}
		}
	}
	

} 
