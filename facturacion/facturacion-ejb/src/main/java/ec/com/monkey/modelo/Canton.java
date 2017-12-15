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
@Table(name = "canton")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canton.findAll", query = "SELECT c FROM Canton c")
    , @NamedQuery(name = "Canton.findByCodigoCa", query = "SELECT c FROM Canton c WHERE c.codigoCa = :codigoCa")
    , @NamedQuery(name = "Canton.findByNombreCa", query = "SELECT c FROM Canton c WHERE c.nombreCa = :nombreCa")
    , @NamedQuery(name = "Canton.findByEstadoCa", query = "SELECT c FROM Canton c WHERE c.estadoCa = :estadoCa")
    , @NamedQuery(name = "Canton.findByUsuarioActCa", query = "SELECT c FROM Canton c WHERE c.usuarioActCa = :usuarioActCa")
    , @NamedQuery(name = "Canton.findByFechaActCa", query = "SELECT c FROM Canton c WHERE c.fechaActCa = :fechaActCa")})
public class Canton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ca")
    private Integer codigoCa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_ca")
    private String nombreCa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_ca")
    private String estadoCa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_ca")
    private int usuarioActCa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_ca")
    @Temporal(TemporalType.DATE)
    private Date fechaActCa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCa")
    private List<Ciudad> ciudadList;
    @JoinColumn(name = "codigo_pro", referencedColumnName = "codigo_pro")
    @ManyToOne(optional = false)
    private Provincia codigoPro;

    public Canton() {
    }

    public Canton(Integer codigoCa) {
        this.codigoCa = codigoCa;
    }

    public Canton(Integer codigoCa, String nombreCa, String estadoCa, int usuarioActCa, Date fechaActCa) {
        this.codigoCa = codigoCa;
        this.nombreCa = nombreCa;
        this.estadoCa = estadoCa;
        this.usuarioActCa = usuarioActCa;
        this.fechaActCa = fechaActCa;
    }

    public Integer getCodigoCa() {
        return codigoCa;
    }

    public void setCodigoCa(Integer codigoCa) {
        this.codigoCa = codigoCa;
    }

    public String getNombreCa() {
        return nombreCa;
    }

    public void setNombreCa(String nombreCa) {
        this.nombreCa = nombreCa;
    }

    public String getEstadoCa() {
        return estadoCa;
    }

    public void setEstadoCa(String estadoCa) {
        this.estadoCa = estadoCa;
    }

    public int getUsuarioActCa() {
        return usuarioActCa;
    }

    public void setUsuarioActCa(int usuarioActCa) {
        this.usuarioActCa = usuarioActCa;
    }

    public Date getFechaActCa() {
        return fechaActCa;
    }

    public void setFechaActCa(Date fechaActCa) {
        this.fechaActCa = fechaActCa;
    }

    @XmlTransient
    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public Provincia getCodigoPro() {
        return codigoPro;
    }

    public void setCodigoPro(Provincia codigoPro) {
        this.codigoPro = codigoPro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCa != null ? codigoCa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canton)) {
            return false;
        }
        Canton other = (Canton) object;
        if ((this.codigoCa == null && other.codigoCa != null) || (this.codigoCa != null && !this.codigoCa.equals(other.codigoCa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Canton[ codigoCa=" + codigoCa + " ]";
    }
    
}
