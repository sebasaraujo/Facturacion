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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
    , @NamedQuery(name = "Provincia.findByCodigoPro", query = "SELECT p FROM Provincia p WHERE p.codigoPro = :codigoPro")
    , @NamedQuery(name = "Provincia.findByNombrePro", query = "SELECT p FROM Provincia p WHERE p.nombrePro = :nombrePro")
    , @NamedQuery(name = "Provincia.findByEstadoPro", query = "SELECT p FROM Provincia p WHERE p.estadoPro = :estadoPro")
    , @NamedQuery(name = "Provincia.findByUsuarioActPro", query = "SELECT p FROM Provincia p WHERE p.usuarioActPro = :usuarioActPro")
    , @NamedQuery(name = "Provincia.findByFechaAcPro", query = "SELECT p FROM Provincia p WHERE p.fechaAcPro = :fechaAcPro")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pro")
    private Integer codigoPro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_pro")
    private String nombrePro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_pro")
    private String estadoPro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_pro")
    private int usuarioActPro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ac_pro")
    @Temporal(TemporalType.DATE)
    private Date fechaAcPro;
    @JoinColumn(name = "codigo_pa", referencedColumnName = "codigo_pa")
    @ManyToOne(optional = false)
    private Pais codigoPa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPro")
    private List<Canton> cantonList;

    public Provincia() {
    }

    public Provincia(Integer codigoPro) {
        this.codigoPro = codigoPro;
    }

    public Provincia(Integer codigoPro, String nombrePro, String estadoPro, int usuarioActPro, Date fechaAcPro) {
        this.codigoPro = codigoPro;
        this.nombrePro = nombrePro;
        this.estadoPro = estadoPro;
        this.usuarioActPro = usuarioActPro;
        this.fechaAcPro = fechaAcPro;
    }

    public Integer getCodigoPro() {
        return codigoPro;
    }

    public void setCodigoPro(Integer codigoPro) {
        this.codigoPro = codigoPro;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public String getEstadoPro() {
        return estadoPro;
    }

    public void setEstadoPro(String estadoPro) {
        this.estadoPro = estadoPro;
    }

    public int getUsuarioActPro() {
        return usuarioActPro;
    }

    public void setUsuarioActPro(int usuarioActPro) {
        this.usuarioActPro = usuarioActPro;
    }

    public Date getFechaAcPro() {
        return fechaAcPro;
    }

    public void setFechaAcPro(Date fechaAcPro) {
        this.fechaAcPro = fechaAcPro;
    }

    public Pais getCodigoPa() {
        return codigoPa;
    }

    public void setCodigoPa(Pais codigoPa) {
        this.codigoPa = codigoPa;
    }

    @XmlTransient
    public List<Canton> getCantonList() {
        return cantonList;
    }

    public void setCantonList(List<Canton> cantonList) {
        this.cantonList = cantonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPro != null ? codigoPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.codigoPro == null && other.codigoPro != null) || (this.codigoPro != null && !this.codigoPro.equals(other.codigoPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Provincia[ codigoPro=" + codigoPro + " ]";
    }
    
}
