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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodigoFc", query = "SELECT f FROM Factura f WHERE f.codigoFc = :codigoFc")
    , @NamedQuery(name = "Factura.findByNumeroFc", query = "SELECT f FROM Factura f WHERE f.numeroFc = :numeroFc")
    , @NamedQuery(name = "Factura.findByFechaFc", query = "SELECT f FROM Factura f WHERE f.fechaFc = :fechaFc")
    , @NamedQuery(name = "Factura.findByEstadoFc", query = "SELECT f FROM Factura f WHERE f.estadoFc = :estadoFc")
    , @NamedQuery(name = "Factura.findByUsuarioActFc", query = "SELECT f FROM Factura f WHERE f.usuarioActFc = :usuarioActFc")
    , @NamedQuery(name = "Factura.findByFechaActFc", query = "SELECT f FROM Factura f WHERE f.fechaActFc = :fechaActFc")
    , @NamedQuery(name = "Factura.findByTotalFc", query = "SELECT f FROM Factura f WHERE f.totalFc = :totalFc")
    , @NamedQuery(name = "Factura.findByValorImpFc", query = "SELECT f FROM Factura f WHERE f.valorImpFc = :valorImpFc")
    , @NamedQuery(name = "Factura.findBySubtotalFc", query = "SELECT f FROM Factura f WHERE f.subtotalFc = :subtotalFc")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_fc")
    private Integer codigoFc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_fc")
    private String numeroFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fc")
    @Temporal(TemporalType.DATE)
    private Date fechaFc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_fc")
    private String estadoFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_act_fc")
    private int usuarioActFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_act_fc")
    @Temporal(TemporalType.DATE)
    private Date fechaActFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_fc")
    private double totalFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_imp_fc")
    private double valorImpFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal_fc")
    private double subtotalFc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFc")
    private List<DetalleFactura> detalleFacturaList;
    @JoinColumn(name = "codigo_ci", referencedColumnName = "codigo_ci")
    @ManyToOne(optional = false)
    private Ciudad codigoCi;
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cl")
    @ManyToOne(optional = false)
    private Cliente codigoCliente;
    @JoinColumn(name = "codigo_emp", referencedColumnName = "codigo_emp")
    @ManyToOne(optional = false)
    private Empleado codigoEmp;
    @JoinColumn(name = "codigo_em", referencedColumnName = "codigo_em")
    @ManyToOne(optional = false)
    private Empresa codigoEm;
    @JoinColumn(name = "codigo_tf", referencedColumnName = "codigo_tf")
    @ManyToOne(optional = false)
    private TipoFactura codigoTf;

    public Factura() {
    }

    public Factura(Integer codigoFc) {
        this.codigoFc = codigoFc;
    }

    public Factura(Integer codigoFc, String numeroFc, Date fechaFc, String estadoFc, int usuarioActFc, Date fechaActFc, double totalFc, double valorImpFc, double subtotalFc) {
        this.codigoFc = codigoFc;
        this.numeroFc = numeroFc;
        this.fechaFc = fechaFc;
        this.estadoFc = estadoFc;
        this.usuarioActFc = usuarioActFc;
        this.fechaActFc = fechaActFc;
        this.totalFc = totalFc;
        this.valorImpFc = valorImpFc;
        this.subtotalFc = subtotalFc;
    }

    public Integer getCodigoFc() {
        return codigoFc;
    }

    public void setCodigoFc(Integer codigoFc) {
        this.codigoFc = codigoFc;
    }

    public String getNumeroFc() {
        return numeroFc;
    }

    public void setNumeroFc(String numeroFc) {
        this.numeroFc = numeroFc;
    }

    public Date getFechaFc() {
        return fechaFc;
    }

    public void setFechaFc(Date fechaFc) {
        this.fechaFc = fechaFc;
    }

    public String getEstadoFc() {
        return estadoFc;
    }

    public void setEstadoFc(String estadoFc) {
        this.estadoFc = estadoFc;
    }

    public int getUsuarioActFc() {
        return usuarioActFc;
    }

    public void setUsuarioActFc(int usuarioActFc) {
        this.usuarioActFc = usuarioActFc;
    }

    public Date getFechaActFc() {
        return fechaActFc;
    }

    public void setFechaActFc(Date fechaActFc) {
        this.fechaActFc = fechaActFc;
    }

    public double getTotalFc() {
        return totalFc;
    }

    public void setTotalFc(double totalFc) {
        this.totalFc = totalFc;
    }

    public double getValorImpFc() {
        return valorImpFc;
    }

    public void setValorImpFc(double valorImpFc) {
        this.valorImpFc = valorImpFc;
    }

    public double getSubtotalFc() {
        return subtotalFc;
    }

    public void setSubtotalFc(double subtotalFc) {
        this.subtotalFc = subtotalFc;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public Ciudad getCodigoCi() {
        return codigoCi;
    }

    public void setCodigoCi(Ciudad codigoCi) {
        this.codigoCi = codigoCi;
    }

    public Cliente getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Cliente codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Empleado getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(Empleado codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public Empresa getCodigoEm() {
        return codigoEm;
    }

    public void setCodigoEm(Empresa codigoEm) {
        this.codigoEm = codigoEm;
    }

    public TipoFactura getCodigoTf() {
        return codigoTf;
    }

    public void setCodigoTf(TipoFactura codigoTf) {
        this.codigoTf = codigoTf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFc != null ? codigoFc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codigoFc == null && other.codigoFc != null) || (this.codigoFc != null && !this.codigoFc.equals(other.codigoFc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.monkey.modelo.Factura[ codigoFc=" + codigoFc + " ]";
    }
    
}
