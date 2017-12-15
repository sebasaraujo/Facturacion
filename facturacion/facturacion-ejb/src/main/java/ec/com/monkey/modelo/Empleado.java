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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCodigoEmp", query = "SELECT e FROM Empleado e WHERE e.codigoEmp = :codigoEmp")
    , @NamedQuery(name = "Empleado.findByNombreEmp", query = "SELECT e FROM Empleado e WHERE e.nombreEmp = :nombreEmp")
    , @NamedQuery(name = "Empleado.findByApellidoEmp", query = "SELECT e FROM Empleado e WHERE e.apellidoEmp = :apellidoEmp")
    , @NamedQuery(name = "Empleado.findByIdentificacionEmp", query = "SELECT e FROM Empleado e WHERE e.identificacionEmp = :identificacionEmp")
    , @NamedQuery(name = "Empleado.findByCorreoEmp", query = "SELECT e FROM Empleado e WHERE e.correoEmp = :correoEmp")
    , @NamedQuery(name = "Empleado.findByTelefonoEmp", query = "SELECT e FROM Empleado e WHERE e.telefonoEmp = :telefonoEmp")
    , @NamedQuery(name = "Empleado.findByEstadoEmp", query = "SELECT e FROM Empleado e WHERE e.estadoEmp = :estadoEmp")
    , @NamedQuery(name = "Empleado.findByUsuarioActEmp", query = "SELECT e FROM Empleado e WHERE e.usuarioActEmp = :usuarioActEmp")
    , @NamedQuery(name = "Empleado.findByFechaActEmp", query = "SELECT e FROM Empleado e WHERE e.fechaActEmp = :fechaActEmp")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_emp")
    private Integer codigoEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_emp")
    private String nombreEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "apellido_emp")
    private String apellidoEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "identificacion_emp")
    private String identificacionEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "correo_emp")
    private String correoEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_emp")
    private String telefonoEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_emp")
    private String estadoEmp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_emp")
    private int usuarioActEmp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_emp")
    @Temporal(TemporalType.DATE)
    private Date fechaActEmp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEmp")
    private List<Factura> facturaList;
    @JoinColumn(name = "codigo_lo", referencedColumnName = "codigo_lo")
    @ManyToOne(optional = false)
    private Local codigoLo;
    @JoinColumn(name = "codigo_ti", referencedColumnName = "codigo_ti")
    @ManyToOne(optional = false)
    private TipoIdentificacion codigoTi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEmp")
    private List<Usuario> usuarioList;

    public Empleado() {
    }

    public Empleado(Integer codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public Empleado(Integer codigoEmp, String nombreEmp, String apellidoEmp, String identificacionEmp, String correoEmp, String telefonoEmp, String estadoEmp, int usuarioActEmp, Date fechaActEmp) {
        this.codigoEmp = codigoEmp;
        this.nombreEmp = nombreEmp;
        this.apellidoEmp = apellidoEmp;
        this.identificacionEmp = identificacionEmp;
        this.correoEmp = correoEmp;
        this.telefonoEmp = telefonoEmp;
        this.estadoEmp = estadoEmp;
        this.usuarioActEmp = usuarioActEmp;
        this.fechaActEmp = fechaActEmp;
    }

    public Integer getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(Integer codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApellidoEmp() {
        return apellidoEmp;
    }

    public void setApellidoEmp(String apellidoEmp) {
        this.apellidoEmp = apellidoEmp;
    }

    public String getIdentificacionEmp() {
        return identificacionEmp;
    }

    public void setIdentificacionEmp(String identificacionEmp) {
        this.identificacionEmp = identificacionEmp;
    }

    public String getCorreoEmp() {
        return correoEmp;
    }

    public void setCorreoEmp(String correoEmp) {
        this.correoEmp = correoEmp;
    }

    public String getTelefonoEmp() {
        return telefonoEmp;
    }

    public void setTelefonoEmp(String telefonoEmp) {
        this.telefonoEmp = telefonoEmp;
    }

    public String getEstadoEmp() {
        return estadoEmp;
    }

    public void setEstadoEmp(String estadoEmp) {
        this.estadoEmp = estadoEmp;
    }

    public int getUsuarioActEmp() {
        return usuarioActEmp;
    }

    public void setUsuarioActEmp(int usuarioActEmp) {
        this.usuarioActEmp = usuarioActEmp;
    }

    public Date getFechaActEmp() {
        return fechaActEmp;
    }

    public void setFechaActEmp(Date fechaActEmp) {
        this.fechaActEmp = fechaActEmp;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Local getCodigoLo() {
        return codigoLo;
    }

    public void setCodigoLo(Local codigoLo) {
        this.codigoLo = codigoLo;
    }

    public TipoIdentificacion getCodigoTi() {
        return codigoTi;
    }

    public void setCodigoTi(TipoIdentificacion codigoTi) {
        this.codigoTi = codigoTi;
    }

    public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEmp != null ? codigoEmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codigoEmp == null && other.codigoEmp != null) || (this.codigoEmp != null && !this.codigoEmp.equals(other.codigoEmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Empleado[ codigoEmp=" + codigoEmp + " ]";
    }
    
}
