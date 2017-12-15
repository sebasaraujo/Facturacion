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
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByCodigoPr", query = "SELECT p FROM Productos p WHERE p.codigoPr = :codigoPr")
    , @NamedQuery(name = "Productos.findByNombrePr", query = "SELECT p FROM Productos p WHERE p.nombrePr = :nombrePr")
    , @NamedQuery(name = "Productos.findByDescripcionPr", query = "SELECT p FROM Productos p WHERE p.descripcionPr = :descripcionPr")
    , @NamedQuery(name = "Productos.findByPrecioPr", query = "SELECT p FROM Productos p WHERE p.precioPr = :precioPr")
    , @NamedQuery(name = "Productos.findByCodigoRefPr", query = "SELECT p FROM Productos p WHERE p.codigoRefPr = :codigoRefPr")
    , @NamedQuery(name = "Productos.findByEstadoPr", query = "SELECT p FROM Productos p WHERE p.estadoPr = :estadoPr")
    , @NamedQuery(name = "Productos.findByUsuarioActPr", query = "SELECT p FROM Productos p WHERE p.usuarioActPr = :usuarioActPr")
    , @NamedQuery(name = "Productos.findByFechaActPr", query = "SELECT p FROM Productos p WHERE p.fechaActPr = :fechaActPr")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pr")
    private Integer codigoPr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_pr")
    private String nombrePr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "descripcion_pr")
    private String descripcionPr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_pr")
    private double precioPr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "codigo_ref_pr")
    private String codigoRefPr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_pr")
    private String estadoPr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_pr")
    private int usuarioActPr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_pr")
    @Temporal(TemporalType.DATE)
    private Date fechaActPr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPr")
    private List<DetalleFactura> detalleFacturaList;
    @JoinColumn(name = "codigo_tp", referencedColumnName = "codigo_tp")
    @ManyToOne(optional = false)
    private TipoProducto codigoTp;

    public Productos() {
    }

    public Productos(Integer codigoPr) {
        this.codigoPr = codigoPr;
    }

    public Productos(Integer codigoPr, String nombrePr, String descripcionPr, double precioPr, String codigoRefPr, String estadoPr, int usuarioActPr, Date fechaActPr) {
        this.codigoPr = codigoPr;
        this.nombrePr = nombrePr;
        this.descripcionPr = descripcionPr;
        this.precioPr = precioPr;
        this.codigoRefPr = codigoRefPr;
        this.estadoPr = estadoPr;
        this.usuarioActPr = usuarioActPr;
        this.fechaActPr = fechaActPr;
    }

    public Integer getCodigoPr() {
        return codigoPr;
    }

    public void setCodigoPr(Integer codigoPr) {
        this.codigoPr = codigoPr;
    }

    public String getNombrePr() {
        return nombrePr;
    }

    public void setNombrePr(String nombrePr) {
        this.nombrePr = nombrePr;
    }

    public String getDescripcionPr() {
        return descripcionPr;
    }

    public void setDescripcionPr(String descripcionPr) {
        this.descripcionPr = descripcionPr;
    }

    public double getPrecioPr() {
        return precioPr;
    }

    public void setPrecioPr(double precioPr) {
        this.precioPr = precioPr;
    }

    public String getCodigoRefPr() {
        return codigoRefPr;
    }

    public void setCodigoRefPr(String codigoRefPr) {
        this.codigoRefPr = codigoRefPr;
    }

    public String getEstadoPr() {
        return estadoPr;
    }

    public void setEstadoPr(String estadoPr) {
        this.estadoPr = estadoPr;
    }

    public int getUsuarioActPr() {
        return usuarioActPr;
    }

    public void setUsuarioActPr(int usuarioActPr) {
        this.usuarioActPr = usuarioActPr;
    }

    public Date getFechaActPr() {
        return fechaActPr;
    }

    public void setFechaActPr(Date fechaActPr) {
        this.fechaActPr = fechaActPr;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public TipoProducto getCodigoTp() {
        return codigoTp;
    }

    public void setCodigoTp(TipoProducto codigoTp) {
        this.codigoTp = codigoTp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPr != null ? codigoPr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.codigoPr == null && other.codigoPr != null) || (this.codigoPr != null && !this.codigoPr.equals(other.codigoPr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Productos[ codigoPr=" + codigoPr + " ]";
    }
    
}
