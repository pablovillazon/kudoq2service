package be.jkin.q2service.model;

//import javax.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public class Kudos {
    //private Long id;
    @Id  private ObjectId _id;
    private String fuente;
    private String destino;
    private String tema;
    private Date fecha;
    private String lugar;
    private String texto;


    //Constructors
    public Kudos(){}

    public Kudos(ObjectId _id, String fuente, String destino, String tema, Date fecha, String lugar, String texto)
    {
        this._id = _id;
        this.fuente = fuente;
        this.destino = destino;
        this.tema = tema;
        this.fecha = fecha;
        this.lugar = lugar;
        this.texto = texto;
    }

    public void setId(ObjectId _id)
    {
        this._id = _id;
    }

    public String getId()
    {
        return this._id.toHexString();
    }

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    */
    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


}
