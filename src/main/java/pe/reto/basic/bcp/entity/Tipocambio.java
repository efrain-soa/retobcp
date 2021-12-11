package pe.reto.basic.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tipocambios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tipocambio {

	    @Id
	    @Column(name = "id")
	    private int id;

	    @Column(name = "convertcode")
	    private String convertCode;
	   
	    @Column(name = "value")
	    private double value;
}

