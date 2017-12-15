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
@Table(name = "tipo_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t")
    , @NamedQuery(name = "TipoIdentificacion.findByCodigoTi", query = "SELECT t FROM TipoIdentificacion t WHERE t.codigoTi = :codigoTi")
    , @NamedQuery(name = "TipoIdentificacion.findByNombreTi", query = "SELECT t FROM TipoIdentificacion t WHERE t.nombreTi = :nombreTi")
    , @NamedQuery(name = "TipoIdentificacion.findByEstadoTi", query = "SELECT t FROM TipoIdentificacion t WHERE t.estadoTi = :estadoTi")
    , @NamedQuery(name = "TipoIdentificacion.findByUsuarioActTi", query = "SELECT t FROM TipoIdentificacion t WHERE t.usuarioActTi = :usuarioActTi")
    , @NamedQuery(name = "TipoIdentificacion.findByFechaActTi", query = "SELECT t FROM TipoIdentificacion t WHERE t.fechaActTi = :fechaActTi")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ti")
    private Integer codigoTi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_ti")
    private String nombreTi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_ti")
    private String estadoTi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_ti")
    private int usuarioActTi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_ti")
    @Temporal(TemporalType.DATE)
    private Date fechaActTi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTi")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTi")
    private List<Empleado> empleadoList;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Integer codigoTi) {
        this.codigoTi = codigoTi;
    }

    public TipoIdentificacion(Integer codigoTi, String nombreTi, String estadoTi, int usuarioActTi, Date fechaActTi) {
        this.codigoTi = codigoTi;
        this.nombreTi = nombreTi;
        this.estadoTi = estadoTi;
        this.usuarioActTi = usuarioActTi;
        this.fechaActTi = fechaActTi;
    }

    public Integer getCodigoTi() {
        return codigoTi;
    }

    public void setCodigoTi(Integer codigoTi) {
        this.codigoTi = codigoTi;
    }

    public String getNombreTi() {
        return nombreTi;
    }

    public void setNombreTi(String nombreTi) {
        this.nombreTi = nombreTi;
    }

    public String getEstadoTi() {
        return estadoTi;
    }

    public void setEstadoTi(String estadoTi) {
        this.estadoTi = estadoTi;
    }

    public int getUsuarioActTi() {
        return usuarioActTi;
    }

    public void setUsuarioActTi(int usuarioActTi) {
        this.usuarioActTi = usuarioActTi;
    }

    public Date getFechaActTi() {
        return fechaActTi;
    }

    public void setFechaActTi(Date fechaActTi) {
        this.fechaActTi = fechaActTi;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTi != null ? codigoTi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoIdentificacion)) {
            return false;
        }
        TipoIdentificacion other = (TipoIdentificacion) object;
        if ((this.codigoTi == null && other.codigoTi != null) || (this.codigoTi != null && !this.codigoTi.equals(other.codigoTi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.TipoIdentificacion[ codigoTi=" + codigoTi + " ]";
    }
    
}
