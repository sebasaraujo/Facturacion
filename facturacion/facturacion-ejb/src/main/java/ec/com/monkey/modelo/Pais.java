/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.findByCodigoPa", query = "SELECT p FROM Pais p WHERE p.codigoPa = :codigoPa")
    , @NamedQuery(name = "Pais.findByNombrePa", query = "SELECT p FROM Pais p WHERE p.nombrePa = :nombrePa")
    , @NamedQuery(name = "Pais.findByEstadoPa", query = "SELECT p FROM Pais p WHERE p.estadoPa = :estadoPa")
    , @NamedQuery(name = "Pais.findByUsuarioActPa", query = "SELECT p FROM Pais p WHERE p.usuarioActPa = :usuarioActPa")
    , @NamedQuery(name = "Pais.findByFechaActPa", query = "SELECT p FROM Pais p WHERE p.fechaActPa = :fechaActPa")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pa")
    private Integer codigoPa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_pa")
    private String nombrePa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_pa")
    private String estadoPa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_pa")
    private int usuarioActPa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_pa")
    @Temporal(TemporalType.DATE)
    private Date fechaActPa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPa")
    private List<Provincia> provinciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPa")
    private List<Empresa> empresaList;

    public Pais() {
    }

    public Pais(Integer codigoPa) {
        this.codigoPa = codigoPa;
    }

    public Pais(Integer codigoPa, String nombrePa, String estadoPa, int usuarioActPa, Date fechaActPa) {
        this.codigoPa = codigoPa;
        this.nombrePa = nombrePa;
        this.estadoPa = estadoPa;
        this.usuarioActPa = usuarioActPa;
        this.fechaActPa = fechaActPa;
    }

    public Integer getCodigoPa() {
        return codigoPa;
    }

    public void setCodigoPa(Integer codigoPa) {
        this.codigoPa = codigoPa;
    }

    public String getNombrePa() {
        return nombrePa;
    }

    public void setNombrePa(String nombrePa) {
        this.nombrePa = nombrePa;
    }

    public String getEstadoPa() {
        return estadoPa;
    }

    public void setEstadoPa(String estadoPa) {
        this.estadoPa = estadoPa;
    }

    public int getUsuarioActPa() {
        return usuarioActPa;
    }

    public void setUsuarioActPa(int usuarioActPa) {
        this.usuarioActPa = usuarioActPa;
    }

    public Date getFechaActPa() {
        return fechaActPa;
    }

    public void setFechaActPa(Date fechaActPa) {
        this.fechaActPa = fechaActPa;
    }

    @XmlTransient
    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPa != null ? codigoPa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.codigoPa == null && other.codigoPa != null) || (this.codigoPa != null && !this.codigoPa.equals(other.codigoPa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Pais[ codigoPa=" + codigoPa + " ]";
    }
    
}
