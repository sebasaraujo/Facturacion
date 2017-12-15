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
@Table(name = "local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l")
    , @NamedQuery(name = "Local.findByCodigoLo", query = "SELECT l FROM Local l WHERE l.codigoLo = :codigoLo")
    , @NamedQuery(name = "Local.findByNombreLo", query = "SELECT l FROM Local l WHERE l.nombreLo = :nombreLo")
    , @NamedQuery(name = "Local.findByDireccionCi", query = "SELECT l FROM Local l WHERE l.direccionCi = :direccionCi")
    , @NamedQuery(name = "Local.findByTelefonoCi", query = "SELECT l FROM Local l WHERE l.telefonoCi = :telefonoCi")
    , @NamedQuery(name = "Local.findByUsuarioActLo", query = "SELECT l FROM Local l WHERE l.usuarioActLo = :usuarioActLo")
    , @NamedQuery(name = "Local.findByFechaActLo", query = "SELECT l FROM Local l WHERE l.fechaActLo = :fechaActLo")
    , @NamedQuery(name = "Local.findByEstadoLo", query = "SELECT l FROM Local l WHERE l.estadoLo = :estadoLo")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_lo")
    private Integer codigoLo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_lo")
    private String nombreLo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "direccion_ci")
    private String direccionCi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_ci")
    private String telefonoCi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_lo")
    private int usuarioActLo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_lo")
    @Temporal(TemporalType.DATE)
    private Date fechaActLo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_lo")
    private String estadoLo;
    @JoinColumn(name = "codigo_ci", referencedColumnName = "codigo_ci")
    @ManyToOne(optional = false)
    private Ciudad codigoCi;
    @JoinColumn(name = "codigo_em", referencedColumnName = "codigo_em")
    @ManyToOne(optional = false)
    private Empresa codigoEm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoLo")
    private List<Empleado> empleadoList;

    public Local() {
    }

    public Local(Integer codigoLo) {
        this.codigoLo = codigoLo;
    }

    public Local(Integer codigoLo, String nombreLo, String direccionCi, String telefonoCi, int usuarioActLo, Date fechaActLo, String estadoLo) {
        this.codigoLo = codigoLo;
        this.nombreLo = nombreLo;
        this.direccionCi = direccionCi;
        this.telefonoCi = telefonoCi;
        this.usuarioActLo = usuarioActLo;
        this.fechaActLo = fechaActLo;
        this.estadoLo = estadoLo;
    }

    public Integer getCodigoLo() {
        return codigoLo;
    }

    public void setCodigoLo(Integer codigoLo) {
        this.codigoLo = codigoLo;
    }

    public String getNombreLo() {
        return nombreLo;
    }

    public void setNombreLo(String nombreLo) {
        this.nombreLo = nombreLo;
    }

    public String getDireccionCi() {
        return direccionCi;
    }

    public void setDireccionCi(String direccionCi) {
        this.direccionCi = direccionCi;
    }

    public String getTelefonoCi() {
        return telefonoCi;
    }

    public void setTelefonoCi(String telefonoCi) {
        this.telefonoCi = telefonoCi;
    }

    public int getUsuarioActLo() {
        return usuarioActLo;
    }

    public void setUsuarioActLo(int usuarioActLo) {
        this.usuarioActLo = usuarioActLo;
    }

    public Date getFechaActLo() {
        return fechaActLo;
    }

    public void setFechaActLo(Date fechaActLo) {
        this.fechaActLo = fechaActLo;
    }

    public String getEstadoLo() {
        return estadoLo;
    }

    public void setEstadoLo(String estadoLo) {
        this.estadoLo = estadoLo;
    }

    public Ciudad getCodigoCi() {
        return codigoCi;
    }

    public void setCodigoCi(Ciudad codigoCi) {
        this.codigoCi = codigoCi;
    }

    public Empresa getCodigoEm() {
        return codigoEm;
    }

    public void setCodigoEm(Empresa codigoEm) {
        this.codigoEm = codigoEm;
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
        hash += (codigoLo != null ? codigoLo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.codigoLo == null && other.codigoLo != null) || (this.codigoLo != null && !this.codigoLo.equals(other.codigoLo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Local[ codigoLo=" + codigoLo + " ]";
    }
    
}
